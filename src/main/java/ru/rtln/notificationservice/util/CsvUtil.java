package ru.rtln.notificationservice.util;

import lombok.NoArgsConstructor;
import ru.rtln.notificationservice.queue.model.payload.report.AnalyticDetailsDto;
import ru.rtln.notificationservice.queue.model.payload.report.StatisticReportDto;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CsvUtil {

    public static String getCsvFromAnalyticDetails(List<AnalyticDetailsDto> analytics) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("userId,productId,working_count,current_count,total_count\n");
        for (AnalyticDetailsDto analyticsDetail : analytics) {
            csvContent.append(analyticsDetail.getUserId()).append(",")
                    .append(analyticsDetail.getProductId()).append(",")
                    .append(analyticsDetail.getTotalWorkingNorm()).append(",")
                    .append(analyticsDetail.getTotalCurrentNorm()).append(",")
                    .append(analyticsDetail.getTotalScore())
                    .append("\n");
        }
        return csvContent.toString();
    }


    public static String getCsvFromReportDetails(List<StatisticReportDto> report) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("productId,count\n");
        for (StatisticReportDto reportDetailsDto : report) {
            csvContent.append(reportDetailsDto.getProductId()).append(",")
                    .append(reportDetailsDto.getCount()).append("\n");
        }
        return csvContent.toString();
    }

    public static String getCsvFromEfficiencyByEmployees(Map<Long, Double> efficiencyByEmployees) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("userId,effectiveness\n");
        for (Map.Entry<Long, Double> longDoubleEntry : efficiencyByEmployees.entrySet()) {
            csvContent.append(longDoubleEntry.getKey()).append(",")
                    .append(longDoubleEntry.getValue())
                    .append("\n");
        }
        return csvContent.toString();
    }
}
