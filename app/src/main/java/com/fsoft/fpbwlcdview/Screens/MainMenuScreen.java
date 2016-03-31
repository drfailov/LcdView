package com.fsoft.fpbwlcdview.Screens;

import android.graphics.Color;
import android.graphics.Rect;

import com.fsoft.fpbwlcdview.Lcd;
import com.fsoft.fpbwlcdview.LcdDraw;
import com.fsoft.fpbwlcdview.LcdView;

/**
 *
 * Created by Dr. Failov on 21.02.2016.
 */
public class MainMenuScreen extends LcdView {


    public MainMenuScreen(Rect viewRect, Lcd lcd) {
        super(viewRect, lcd);
        addView(new MenuScreen(getViewRect(), getLcd())
                        .addMenuItem("Сообщения", new Runnable() {
                            @Override
                            public void run() {
                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                        .addMenuItem("Новое сообщение", null)
                                        .addMenuItem("Входящие", null)
                                        .addMenuItem("Черновики", null)
                                        .addMenuItem("Исходящие", null)
                                        .addMenuItem("Переданные", null)
                                        .addMenuItem("Сохраненные", null)
                                        .addMenuItem("Отчеты о доставке", null)
                                        .addMenuItem("Эл. почта", null)
                                        .addMenuItem("Чат", null)

                                        .setHeaderText("Сообщения")
                                        .setPage(0));
                            }
                        })
                        .addMenuItem("Контакты", new Runnable() {
                            @Override
                            public void run() {
                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                        .addMenuItem("Имена", null)
                                        .addMenuItem("Синхронизировать", null)
                                        .addMenuItem("Настройки", null)
                                        .addMenuItem("Группы", null)
                                        .addMenuItem("Быстрый набор", null)
                                        .addMenuItem("Удал. все конт.", null)
                                        .addMenuItem("Переместить", null)
                                        .addMenuItem("Скопировать", null)

                                        .setHeaderText("Контакты")
                                        .setPage(0));
                            }
                        })
                        .addMenuItem("Журнал", new Runnable() {
                            @Override
                            public void run() {
                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                        .addMenuItem("Вызовы", null)
                                        .addMenuItem("Непринятые", null)
                                        .addMenuItem("Принятые", null)
                                        .addMenuItem("Набранные", null)
                                        .addMenuItem("Очистить исорию", null)

                                        .setHeaderText("Журнал")
                                        .setPage(0));
                            }
                        })
                        .addMenuItem("Настройки", new Runnable() {
                            @Override
                            public void run() {
                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                        .addMenuItem("Режимы", null)
                                        .addMenuItem("Темы", null)
                                        .addMenuItem("Сигналы", null)
                                        .addMenuItem("Дисплей", new Runnable() {
                                            @Override
                                            public void run() {
                                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                                        .addMenuItem("Цвет подсветки", new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                                                        .addMenuItem("Нет подсветки", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#353731"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Барюзовая", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#8cb8b3"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Зеленая", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#677a4d"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Зеленая2", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#007a31"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Желто зеленая", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#87af32"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Синяя", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#0030ff"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Голубая", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#97deff"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Оранжевая", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#c3852d"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Красная", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#c4283e"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Белая", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#FFFFFF"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Белая2", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBackgroundColor(Color.parseColor("#c4c4ff"));
                                                                            }
                                                                        })

                                                                        .setHeaderText("Цвет фона")
                                                                        .setPage(0));
                                                            }
                                                        })
                                                        .addMenuItem("Цвет пикселей", new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                                                        .addMenuItem("Черный", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBasePixelColor(Color.parseColor("#000000"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Красный", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBasePixelColor(Color.parseColor("#980000"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Зеленый", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBasePixelColor(Color.parseColor("#007a31"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Синий", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBasePixelColor(Color.parseColor("#001296"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Фиолетовый", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBasePixelColor(Color.parseColor("#410083"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Ярко красный", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBasePixelColor(Color.parseColor("#FF0000"));
                                                                            }
                                                                        })
                                                                        .addMenuItem("Ярко зеленый", new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                getLcd().setBasePixelColor(Color.parseColor("#00FF00"));
                                                                            }
                                                                        })

                                                                        .setHeaderText("Цвет пикселей")
                                                                        .setPage(0));
                                                            }
                                                        })
                                                        .addMenuItem("Инвертировать дисплей", new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                getLcd().invertScreen();
                                                            }
                                                        })
                                                        .addMenuItem("Инфо о дисплее", null)

                                                        .setHeaderText("Дисплей")
                                                        .setPage(0));
                                            }
                                        })
                                        .addMenuItem("Время и дата", null)
                                        .addMenuItem("Быстрый доступ", null)
                                        .addMenuItem("Подключения", null)
                                        .addMenuItem("Вызовы", null)
                                        .addMenuItem("Телефон", null)
                                        .addMenuItem("Аксессуары", null)
                                        .addMenuItem("Конфигурация", null)
                                        .addMenuItem("Защита", null)

                                        .setHeaderText("Настройки")
                                        .setPage(0));
                            }
                        })
                        .addMenuItem("Галерея", new Runnable() {
                            @Override
                            public void run() {
                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                        .addMenuItem("Фото", null)
                                        .addMenuItem("Клипы", null)
                                        .addMenuItem("Музыка", null)
                                        .addMenuItem("Темы", null)
                                        .addMenuItem("Графика", null)
                                        .addMenuItem("Сигналы", null)
                                        .addMenuItem("Аудиоаписи", null)
                                        .addMenuItem("Принятые", null)

                                        .setHeaderText("Галерея")
                                        .setPage(0));
                            }
                        })
                        .addMenuItem("Мультимедиа", null)
                        .addMenuItem("Интернет", null)
                        .addMenuItem("Органайзер", null)
                        .addMenuItem("Приложения", new Runnable() {
                            @Override
                            public void run() {
                                getLcd().addScreen(new MenuScreen(getViewRect(), getLcd())
                                        .addMenuItem("Рисовалка", new Runnable() {
                                            @Override
                                            public void run() {
                                                getLcd().addScreen(new DrawApplication(getViewRect(), getLcd()));
                                            }
                                        })

                                        .setHeaderText("Приложения")
                                        .setPage(0));
                            }
                        })
                        .addMenuItem("РТТ", null)
                        .setHeaderText("Меню")
                        .setPage(0)
        );
    }
}
