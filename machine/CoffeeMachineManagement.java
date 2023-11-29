package machine;

public class CoffeeMachineManagement {
    private int money;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;

    private boolean canAcceptPay = false;

    public CoffeeMachineManagement() {
        this.money = 550;
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.cups = 9;
    }

    public void setMoney(int money) {
        this.money += money;
    }

    public void setWater(int water) {
        this.water += water;
    }

    public void setMilk(int milk) {
        this.milk += milk;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans += coffeeBeans;
    }

    public void setCups(int cups) {
        this.cups += cups;
    }

    public int deductAllAmountMoney() {
        int amount = this.money;
        this.money -= this.money;
        return amount;
    }

    public void deductWater(int water) {
        if (checkResourceForDeduction(water, 0, 0, 0))
            this.water -= water;
    }

    public void deductMilk(int milk) {
        if (checkResourceForDeduction(0, milk, 0, 0))
            this.milk -= milk;
    }

    public void deductCoffeeBeans(int coffeeBeans) {
        if (checkResourceForDeduction(0, 0, coffeeBeans, 0))
            this.coffeeBeans -= coffeeBeans;
    }

    public void deductCups(int cups) {
        if (checkResourceForDeduction(0, 0, 0, cups))
            this.cups -= cups;
    }

    public String stateOfCoffeeMachine() {
        return """
                The coffee machine has:
                %s ml of water
                %s ml of milk
                %s g of coffee beans
                %s disposable cups
                $%s of money
                """.formatted(this.water, this.milk, this.coffeeBeans, this.cups, this.money);
    }

    public boolean checkResources(String typeCoffee) {
        switch (typeCoffee.toLowerCase()) {
            case "espresso" -> System.out.println(resourceLacked(250, 1, 16));
            case "latte" -> System.out.println(resourceLacked(350, 75 , 20));
            case "cappuccino" -> System.out.println(resourceLacked(200, 100, 12));
        }
        System.out.println(); // Just for the new line
        return this.canAcceptPay;
    }

    private String resourceLacked(int water, int milk, int coffeeBean) {
        int maxCupCoffee = Math.min(Math.min(this.water / water, this.milk / milk), this.coffeeBeans / coffeeBean);
        String sorryMessage = null;
        if (maxCupCoffee > 0 ) {
            this.canAcceptPay = true;
            return "I have enough resources, making you a coffee!";
        } else {
            this.canAcceptPay = false;
            int minResource = Math.min(Math.min(this.water, this.milk), this.coffeeBeans);
            if (minResource == this.water)
                sorryMessage =  "Sorry, not enough water!";
            if (minResource == this.milk)
                sorryMessage =  "Sorry, not enough milk!";
            if (minResource == this.coffeeBeans)
                sorryMessage =  "Sorry, not enough coffee beans!";
        }
        return sorryMessage;
    }

    private boolean checkResourceForDeduction(int water, int milk, int coffeeBeans, int cups) {
        int checkWater = this.water - water;
        int checkMilk = this.milk - milk;
        int checkCoffeeBeans = this.coffeeBeans - coffeeBeans;
        int checkCups = this.cups - cups;
        return checkWater >= 0 &&
                checkMilk >= 0 &&
                checkCoffeeBeans >= 0 &&
                checkCups >= 0;
    }
}
