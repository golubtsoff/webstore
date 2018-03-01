package service;

import entity.Item;
import entity.Person;
import entity.Purchase;
import entity.Role;
import exception.DBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdminServiceTest {

    private final PersonService personService = new PersonServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createItem() throws Exception {
        Item item = new Item("Планшет обучающий мини",
                "Интерактивный обучающий планшет поможет подготовить малыша к школьной программе и освоить ему русский алфавит, пополнить свой словарный запас новыми словами, изучить цифры и основы счёта, а также музыкальные ноты. Игрушка, выполненная в виде современного гаджета, работает в 7 программных режимах. Малыш сможет сам проверить, насколько хорошо он усвоил материал в режиме «тест» или «экзамен». На планшете предусмотрена кнопка регулировки громкости. Интерактивная игра поможет ребенку развить память, внимательность, восприятие и усидчивость. Простой, яркий и забавный интерфейс не позволит заскучать.",
                new BigDecimal(470),
                3);
        Long itemId = adminService.createItem(item);
        Item testItem = personService.getItem(itemId);
        assertEquals("Wrong create item", item, testItem);
    }

    @Test(expected = DBException.class)
    public void createItemException() throws Exception {
        Item item = new Item(
                "Настольная игра Имаджинариум Юбилейное издание ",
                "«Имаджинариум. 5 лет» — настольная игра, в которой вам предстоит придумывать ассоциации к интереснейшим картинкам, выпущенная к юбилею популярной серии. Издание с новыми волшебными иллюстрациями, яркими стеклянными фигурками игрока (индивидуальные, неповторяющиеся) и удобнейшим органайзером для хранения элементов и дополнений!",
                new BigDecimal(2250),
                2);
        adminService.createItem(item);
        adminService.createItem(item);
    }

    @Test
    public void updateItem() throws Exception {
        Item item = new Item("Конструктор Самоделкин цветной 80 моделей",
                "Конструктор \"Самоделкин цветной 80 моделей\"- настоящая классика среди игрушек подобного рода. Такие же конструкторы были популярны в советское время и использовались на уроках труда в младших классах. А у сегодняшних родителей есть шанс подарить ребенку игрушку из своего детства. В состав набора входит 307 деталей. Большинство основных деталей окрашено, что делает готовые модели более яркими.",
                new BigDecimal(865),
                10);
        Long itemId = adminService.createItem(item);

        item.setTitle("Конструктор Самоделкин цветной 10 моделей");
        adminService.updateItem(item);

        Item testItem = personService.getItem(itemId);
        assertEquals("Wrong update item", item, testItem);
    }

    @Test (expected = DBException.class)
    public void updateItemException() throws Exception {
        adminService.updateItem(new Item());
    }

    @Test
    public void deleteItem() throws Exception {
        Item item = new Item("Настольная игра Стулья",
                "Настольная игра Стулья – занимательное развлечение для всей семьи. Смысл игры сводится к тому, чтобы построить гору из стульев: каждый игрок по очереди ставит один стул так, чтобы конструкция не разрушилась. Чтобы усложнить правила, можно вначале построить такую гору, а затем каждый игрок по очереди убирает один стул. Проиграет тот игрок, во время хода которого конструкция обрушится.\n" +
                        "\n" +
                        "Настольная игра Стулья развивает мелкую моторику рук, координацию и точность движений, а также логическое мышление.",
                new BigDecimal(986),
                17);
        Long itemId = adminService.createItem(item);
        adminService.deleteItem(itemId);
        Item testItem = adminService.getItem(itemId);
        assertNull("Item not deleted", testItem);
    }

    @Test (expected = DBException.class)
    public void deleteItemException() throws Exception {
        adminService.deleteItem(-1);
    }

    @Test
    public void getPurchases() throws Exception {
        Person person = personService.signUp("Bob", "pass345");

        UserService userService = new UserServiceImpl(person);
        Item item = new Item("Гравитационный 3D лабиринт",
                "\"Гравитационный 3D-лабиринт\" - объемная головоломка от компании ThinkFun, увидевшая свет в 2017 году. Решая ее задания, игроку предстоит проложить маршрут для металлического шарика, который скатывается под собственным весом по выстраиваемому лабиринту.\n" +
                        "\n" +
                        "      Игра-головоломка \"Гравитационный 3D Лабиринт\", в основе которой Закон всемирного тяготения, несомненно, станет достойным испытанием для ваших умственных способностей и пространственного воображения. ",
                new BigDecimal(3290),
                3);

        Long itemId = adminService.createItem(item);
        userService.setPurchase(itemId, 1);
        userService.setPurchase(itemId, 1);
        userService.setPurchase(itemId, 1);

        List<Purchase> testPurchases = adminService.getPurchases();
        for (Purchase purchase : testPurchases){
            assertEquals("Person is not same", purchase.getPerson(), person);
            assertEquals("Item is not same", purchase.getItem(), item);
            assertEquals("Price is not same", purchase.getCost(), item.getPrice());
            assertEquals("Item is not same", purchase.getAmount(), 1);
        }
    }

}