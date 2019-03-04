package data;

import entity.Item;
import service.AdminService;
import service.PersonService;
import service.ServiceFactory;
import service.UserService;

import java.math.BigDecimal;

public class TestData {
    public static final PersonService personService = ServiceFactory.getPersonService();
    public static final AdminService adminService = ServiceFactory.getAdminService();
    public static final UserService userService = ServiceFactory.getUserService();

    public static final Item item1;
    public static final Item item2;
    public static final Item item3;
    public static final Item item4;

    static {
        item1 = new Item("Планшет обучающий мини",
                "Интерактивный обучающий планшет поможет подготовить малыша к школьной программе и освоить ему русский алфавит, пополнить свой словарный запас новыми словами, изучить цифры и основы счёта, а также музыкальные ноты. Игрушка, выполненная в виде современного гаджета, работает в 7 программных режимах. Малыш сможет сам проверить, насколько хорошо он усвоил материал в режиме «тест» или «экзамен». На планшете предусмотрена кнопка регулировки громкости. Интерактивная игра поможет ребенку развить память, внимательность, восприятие и усидчивость. Простой, яркий и забавный интерфейс не позволит заскучать.",
                new BigDecimal(470),
                3);
        item2 = new Item(
                "Настольная игра Имаджинариум Юбилейное издание ",
                "«Имаджинариум. 5 лет» — настольная игра, в которой вам предстоит придумывать ассоциации к интереснейшим картинкам, выпущенная к юбилею популярной серии. Издание с новыми волшебными иллюстрациями, яркими стеклянными фигурками игрока (индивидуальные, неповторяющиеся) и удобнейшим органайзером для хранения элементов и дополнений!",
                new BigDecimal(2250),
                2);
        item3 = new Item("Конструктор Самоделкин цветной 80 моделей",
                "Конструктор \"Самоделкин цветной 80 моделей\"- настоящая классика среди игрушек подобного рода. Такие же конструкторы были популярны в советское время и использовались на уроках труда в младших классах. А у сегодняшних родителей есть шанс подарить ребенку игрушку из своего детства. В состав набора входит 307 деталей. Большинство основных деталей окрашено, что делает готовые модели более яркими.",
                new BigDecimal(865),
                0);
        item4 = new Item("Настольная игра Стулья",
                "Настольная игра Стулья – занимательное развлечение для всей семьи. Смысл игры сводится к тому, чтобы построить гору из стульев: каждый игрок по очереди ставит один стул так, чтобы конструкция не разрушилась. Чтобы усложнить правила, можно вначале построить такую гору, а затем каждый игрок по очереди убирает один стул. Проиграет тот игрок, во время хода которого конструкция обрушится.\n" +
                        "\n" +
                        "Настольная игра Стулья развивает мелкую моторику рук, координацию и точность движений, а также логическое мышление.",
                new BigDecimal(986),
                17);

    }
}