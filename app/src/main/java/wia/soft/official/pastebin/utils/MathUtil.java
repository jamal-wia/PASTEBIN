package wia.soft.official.pastebin.utils;

/**
 * Утилита для расчётов.
 */
public class MathUtil {

    /**
     * Вычисление факториала
     * @param number - Число от которого надо найти факториал
     * @return - расчитанное значение факториала.
     */
    public static int factorial(final int number){

        if (number == 0) {

            return 1;
        } else {

            return number * factorial(number - 1);
        }
    }

    /**
     * Метод для округления значений
     * @param x - значение которое надо округлить
     * @param numberOfDecimalPlaces - число знаков после запятой
     * @return - округлённое значение
     * @throws IllegalArgumentException некоректные аргументы
     */
    public static Double round(final double x, final int numberOfDecimalPlaces) throws IllegalArgumentException {

        if(numberOfDecimalPlaces < 0){
            throw new IllegalArgumentException("numberOfDecimalPlaces < 0");
        }
        int scaleDecimal = (int) Math.pow(10, numberOfDecimalPlaces);

        double iX = (x * scaleDecimal);
        double tmp = Math.rint(iX);

        return tmp / scaleDecimal;
    }

    /**
     * Класс для округлений. Работает чуть быстрее при множественном использовании и делает код более читабельным.
     */
    public static class Rounder {

        /**
         * Число знаков после запятой
         */
        final int numberOfDecimalPlaces;
        /**
         * Коэфициент который расчитывается для ускорения округления при множественном использовании.
         */
        final int scaleDecimal;

        public Rounder(int numberOfDecimalPlaces) {

            if(numberOfDecimalPlaces < 0){
                throw new ArithmeticException("numberOfDecimalPlaces < 0");
            }

            this.numberOfDecimalPlaces = numberOfDecimalPlaces;
            scaleDecimal = (int) Math.pow(10, numberOfDecimalPlaces);
        }

        public double round(final double x){

            double iX = (x * scaleDecimal);
            double roundNumber = Math.rint(iX);

            return roundNumber / scaleDecimal;
        }

        public int getNumberOfDecimalPlaces() {
            return numberOfDecimalPlaces;
        }
    }
}