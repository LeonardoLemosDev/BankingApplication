package br.dev.leonardolemos.service;

import static br.dev.leonardolemos.enums.TitlesEnum.*;

public class MenuService {

    public static void showMainMenu() {
        showMenuTitle(APP_NAME.toString());
        System.out.println(MAIN_MENU_OPTION_1);
        System.out.println(MAIN_MENU_OPTION_2);
        System.out.println(MAIN_MENU_OPTION_3);
        System.out.println(MAIN_MENU_OPTION_4);
        System.out.println(MAIN_MENU_OPTION_5);
        System.out.println(MAIN_MENU_OPTION_6);
        System.out.println(MAIN_MENU_OPTION_7);
        System.out.println(MAIN_MENU_OPTION_8);
        System.out.println(MAIN_MENU_OPTION_9);
        System.out.println(MAIN_MENU_OPTION_0);
        System.out.print(CHOOSE_AN_OPTION);
    }

    public static String menuTitle(String title) {
        int width = 50;
        StringBuilder sb = new StringBuilder();

        // Line 1
        sb.append("=".repeat(width)).append("\n");

        // Line 2 - Centered text
        int spaces = (width - title.length()) / 2;
        sb.append(" ".repeat(spaces)).append(title).append("\n");

        // Line 3
        sb.append("=".repeat(width));

        return sb.toString();
    }

    public static void showMenuTitle(String title) {
        System.out.println(menuTitle(title));
    }

    public static void showDivider(){
        int width = 50;

        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat(width));

        System.out.println(sb);
    }

}
