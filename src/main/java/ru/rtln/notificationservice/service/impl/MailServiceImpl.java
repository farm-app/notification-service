package ru.rtln.notificationservice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.exceptions.TemplateProcessingException;
import ru.rtln.notificationservice.entity.SentParticipantSystemEvent;
import ru.rtln.notificationservice.entity.SystemEventParticipant;
import ru.rtln.notificationservice.redis.repository.RedisUserRepository;
import ru.rtln.notificationservice.repository.SentParticipantSystemEventRepository;
import ru.rtln.notificationservice.service.MailService;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private final RedisUserRepository redisUserRepository;
    private final SentParticipantSystemEventRepository sentParticipantSystemEventRepository;
    private final JavaMailSender mailSender;


    public static final String DESCRIPTION_FOR_REPORT = """
                Отчетная информация включает данные о всех произведенных продуктов фермы
                за сегодняшний день. Данные представлены в виде таблицы.
                Колонки: Продукт, Название, Единица измерения, произведенное количество.
            """;

    public static final String DESCRIPTION_FOR_ANALYTICS = """
                Аналитическая информация включает данные об эффективности работы
                фермы на основании выданных норм. Данные представлены в виде таблицы.
                Колонки: сотрудник, товар, заданная норма, произведенное количество, эффективность(КПД в %).
                Если для одного сотрудника выдавалась норма сбора по одному и тому же продукту несколько раз в день,
                то происходит группировка и в отчете представлены суммарные значения.
                КПД рассчитывается как отношение собранной продукции к заданной норме.
            """;

    public static final String DESCRIPTION_FOR_EMPLOYEE_ANALYTICS = """
                Аналитическая информация включает данные об общей эффективности работы
                сотрудника за текущий день. Данные представлены в виде таблицы.
                Колонки: сотрудник, его эффективность(КПД в %).
                КПД рассчитывается как отношение собранной продукции к заданной норме.           
            """;


    @Override
    @EventListener
    public void sendMail(SystemEventParticipant event) {
        var sentParticipantSystemEvent = new SentParticipantSystemEvent(event.getId(), true);
        try {
            sendEmail(event);
            sentParticipantSystemEvent.setSent(true);
        } catch (MessagingException | UnsupportedEncodingException | TemplateProcessingException e) {
            sentParticipantSystemEvent.setSent(true);
            log.error("Error occurred", e);
        }finally {
            sentParticipantSystemEventRepository.save(sentParticipantSystemEvent);
        }
    }

    private void sendEmail(SystemEventParticipant event) throws MessagingException, UnsupportedEncodingException {
        switch (event.getSystemEvent().getType()) {
            case DAILY_PRODUCT_REPORT:
                sendDailyProductReport(event.getUserId(), event.getSystemEvent().getContent());
                break;
            case DAILY_EMPLOYEE_ANALYTICS:
                sendDailyEmployeeAnalytics(event.getUserId(), event.getSystemEvent().getContent());
                break;
            case DAILE_EMPLOYEE_SCORE_ANALYTICS:
                sendDailyEmployeeScoreAnalytics(event.getUserId(), event.getSystemEvent().getContent());
                break;
            case DAILY_EMPLOYEE_SCORE:
                sendDailyEmployeeScore(event.getUserId(), event.getSystemEvent().getContent());
                break;
        }
    }

    @SneakyThrows
    private void sendDailyProductReport(Long userId, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        var email = redisUserRepository.get(userId).getEmail();
        helper.setTo(email);
        helper.setSubject(String.format("Отчетная информация о произведенных товаров на ферме за %s",
                LocalDate.now()));
        helper.setText(DESCRIPTION_FOR_REPORT);
        String fileName = String.format("report_%s.csv", LocalDate.now());
        helper.addAttachment(fileName, new ByteArrayResource(content.getBytes()));
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private void sendDailyEmployeeScoreAnalytics(Long userId, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        var email = redisUserRepository.get(userId).getEmail();
        helper.setTo(email);
        helper.setSubject(String.format("Аналитический отчет по эффективности всех работников фермы %s",
                LocalDate.now()));
        helper.setText(DESCRIPTION_FOR_EMPLOYEE_ANALYTICS);
        String fileName = String.format("analytics-employees_%s.csv", LocalDate.now());
        helper.addAttachment(fileName, new ByteArrayResource(content.getBytes()));
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private void sendDailyEmployeeAnalytics(Long userId, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        var email = redisUserRepository.get(userId).getEmail();
        helper.setTo(email);
        helper.setSubject(String.format("Аналитическая информация по работе фермы за %s", LocalDate.now()));
        helper.setText(DESCRIPTION_FOR_ANALYTICS);
        String fileName = String.format("analytics_%s.csv", LocalDate.now());
        helper.addAttachment(fileName, new ByteArrayResource(content.getBytes()));
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private void sendDailyEmployeeScore(Long userId, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        var email = redisUserRepository.get(userId).getEmail();
        helper.setTo(email);
        helper.setSubject(String.format("Ваша оценка эффективности за %s", LocalDate.now()));
        helper.setText("КПД:" + content + "%");
        mailSender.send(mimeMessage);
    }

}
