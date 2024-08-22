package com.example.wantedtestassigment.InsuranceTask;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Написать функцию, определяющую ближайшую дату отправки списка в страховую с условием, что отправка осуществляется
// 1, 10 и 20 числа каждого месяца в 18:00. Если дата отправки попадает на рабочий/праздничный день - то отправка осуществляется в предыдущий рабочий день.
// Дата запроса == текущему времени.
// Можно использовать функцию: public getVacCheck(java.sql.Date modDate); - проверяет дату, является ли она рабочей.
// Если выходной - возвращает ближайший рабочий день следующий за выходными. Возвращает переменную типа java.sql.Date

public class InsuranceDateCalculator {

    /**
     * Метод для определения ближайшей даты отправки списка в страховую
     *
     * @return дата отправки
     */
    public Date getNextInsuranceSubmissionDate() {
        List<LocalDateTime> possibleDates = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() <= 20) {
            addPossibleDates(now.toLocalDate(), possibleDates);
        } else {
            addPossibleDates(now.plusMonths(1).toLocalDate(), possibleDates);
        }

        for (LocalDateTime date : possibleDates) {
            LocalDateTime checkedDateTime = adjustToWorkingDay(date);
            if (checkedDateTime.isAfter(now)) {
                return Date.valueOf(checkedDateTime.toLocalDate());
            }
        }

        return null;
    }

    /**
     * Метод для добавления дат 1, 10, 20 числа в список возможных дат
     *
     * @param baseDate      текущая\ближайшая дата
     * @param possibleDates возможные для отправки даты
     */
    private void addPossibleDates(LocalDate baseDate, List<LocalDateTime> possibleDates) {
        int[] days = {1, 10, 20};
        for (int day : days) {
            if (baseDate.getDayOfMonth() <= day || baseDate.getMonthValue() != LocalDate.now().getMonthValue()) {
                possibleDates.add(LocalDateTime.of(baseDate.withDayOfMonth(day), LocalTime.of(18, 0)));
            }
        }
    }

    /**
     * Метод для проверки и корректировки даты на рабочий день
     *
     * @param dateTime выбранная дата
     * @return скорректированная дата
     */
    private LocalDateTime adjustToWorkingDay(LocalDateTime dateTime) {
        Date modDate = Date.valueOf(dateTime.toLocalDate());
        Date checkedDate = getVacCheck(modDate);
        if (!checkedDate.toLocalDate().equals(dateTime.toLocalDate())) {
            return checkedDate.toLocalDate().atTime(18, 0);
        }
        return dateTime;
    }

    /**
     * Заглушка для метода проверки рабочего дня
     */
    public Date getVacCheck(Date modDate) {
        return modDate;
    }
}
