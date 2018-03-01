package util;

import entity.Item;
import entity.Person;
import entity.Role;
import exception.DBException;
import exception.ServiceException;
import service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public void fillDB(){
        try {
            addPersons();
            addItems();
            addPurchases();
        } catch (DBException | ServiceException e) {
            e.printStackTrace();
        }
    }

    private void addPersons() throws DBException {
        // sign up person
        PersonService personService = new PersonServiceImpl();
        personService.signUp("root", "123", Role.admin);
        personService.signUp("user", "345");
    }

    private void addItems() throws DBException {
        PersonService personService = new PersonServiceImpl();
        personService.signUp("alice", "111");
        AdminService adminService = new AdminServiceImpl();
        List<Item> items = new ArrayList<>();
        items.add(new Item("Конструктор Самоделкин цветной 80 моделей",
                "Конструктор \"Самоделкин цветной 80 моделей\"- настоящая классика среди игрушек подобного рода. Такие же конструкторы были популярны в советское время и использовались на уроках труда в младших классах. А у сегодняшних родителей есть шанс подарить ребенку игрушку из своего детства. В состав набора входит 307 деталей. Большинство основных деталей окрашено, что делает готовые модели более яркими.",
                new BigDecimal(865),
                10));
        items.add(new Item("Планшет обучающий мини",
                "Интерактивный обучающий планшет поможет подготовить малыша к школьной программе и освоить ему русский алфавит, пополнить свой словарный запас новыми словами, изучить цифры и основы счёта, а также музыкальные ноты. Игрушка, выполненная в виде современного гаджета, работает в 7 программных режимах. Малыш сможет сам проверить, насколько хорошо он усвоил материал в режиме «тест» или «экзамен». На планшете предусмотрена кнопка регулировки громкости. Интерактивная игра поможет ребенку развить память, внимательность, восприятие и усидчивость. Простой, яркий и забавный интерфейс не позволит заскучать.",
                new BigDecimal(470),
                3));
        items.add(new Item("Фокусы с наперстками и маркировкой",
                "Настольная игра с наперстками и маркировкой – подарит детям возможность научиться выполнять различные трюки. Дети с удовольствием будут показывать захватывающие фокусы своим друзьям и близким. С этим набором дети смогут научиться выполнять 10 фокусов с наперстками, а если собрать всю серию, то фокусы можно будет комбинировать. Благодаря подробной цветной инструкции дети быстро и легко выучат все трюки.",
                new BigDecimal(531),
                1));
        for (Item item : items) {
            Long id = adminService.createItem(item);
        }

    }

    private void addPurchases() throws DBException, ServiceException {
        PersonService personService = new PersonServiceImpl();
        Person person = personService.signUp("user2", "secret");
        AdminService adminService = new AdminServiceImpl();
        Item item = new Item(
                "Настольная игра Имаджинариум Юбилейное издание ",
                "«Имаджинариум. 5 лет» — настольная игра, в которой вам предстоит придумывать ассоциации к интереснейшим картинкам, выпущенная к юбилею популярной серии. Издание с новыми волшебными иллюстрациями, яркими стеклянными фигурками игрока (индивидуальные, неповторяющиеся) и удобнейшим органайзером для хранения элементов и дополнений!",
                new BigDecimal(2250),
                2);
        Long itemId = adminService.createItem(item);

        UserService userService = new UserServiceImpl(person);
        userService.setPurchase(itemId, 1);
    }
}
