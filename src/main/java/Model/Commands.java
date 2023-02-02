package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands{

    LoginReq("Login (?<username>\\S+)"),
    CREATEACCOUNT("create account (?<type>\\S+) (?<username>\\S+)"),
    signUpReq("sign up"),
    signUpForm("\\s*create\\s+account\\s+(?<(role)>\\S+)\\s+(?<userName>(?=.*[a-z])(?=.*[A-Z]))\\s*"),
    loginForm("\\s*Login\\s+(?<userName>(?=.*[a-z])(?=.*[A-Z]))"),
    viewPersonalInfo("\\s*view\\s+personal\\s+info"),
    editPersonalInfo("edit\\s+(?<field>\\S+)"),
    manageUser("\\s*manage\\s+user\\s*"),
    viewForAdmin("\\s*view\\s+(?<userName>(?=.*[a-z])(?=.*[A-Z]))"),
    changeTypeForAdmin("\\s*change\\s+type(?<userName>(?=.*[a-z])(?=.*[A-Z]))\\s+(?<role>\\w+)"),
    deleteUser("delete\\s+user\\s+(?<user>\\S+)"),
    manageProducts("\\s*manage\\s+all\\s+products"),
    createDiscountCode("\\s*create\\s+discount\\s+code"),
    date("(?<year>\\d+)\\/(?<month>\\d+)\\/(?<day>\\d+)"),
    manageCategory("\\s*manage\\s+categories"),
    editInfo("\\s*edit\\s+(?<field>\\S+)"),
    addInfo("\\s*add\\s+(?<field>\\S+)"),
    remove("\\s*remove\\s+(?<field>\\S+)"),
    findProductWithID("\\s*find\\s+product\\s+with\\s+id\\s+(?<id>\\d+)"),
    getFindProductWithName("\\s*find\\s+product\\s+with\\s+name\\s+(?<name>\\S+)"),
    comment("\\s*comment\\s+(?<name>\\S+)"),
    findPerson("\\s*find\\s+person\\s+(?<personName>\\S+)"),
    getCategory("\\s*get\\s+category\\s+(?<category>\\S+)"),
    createCategory("\\s*create\\s+category\\s+(?<name>\\S+)"),
    sellerMangeProducts("manage\\s+products"),
    addProduct("\\s*add\\s+product\\s*"),
    removeProduct("\\s*remove\\s+product\\s+(?<id>\\d+)"),
    back("back"),
    setScore("\\s*set\\s+score\\s+(?<name>\\S+)"),
    buyProductWithName("\\s*buy\\s+product\\s+with\\s+name\\s+(?<name>\\S+)"),
    buyProductWithID("\\s*buy\\s+product\\s+with\\s+id\\s+(?<id>\\d+)"),
    addMoney("\\s*add\\s+money\\s+(?<amount>\\d+)"),
    help("\\s*help");

    private String regex;

    Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands command) {
        Matcher matcher = Pattern.compile(command.regex, Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
