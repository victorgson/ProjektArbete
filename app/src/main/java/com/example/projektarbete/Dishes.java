package com.example.projektarbete;

public class Dishes extends android.app.Activity {
    private String name;
    private String info;
    private String price;
    private int prices;


    public Dishes() {
    }

    public Dishes(String name, String info, String price, int prices) {

        this.name = name;
        this.info = info;
        this.price = price;
        this.prices = prices;


    }

    public Dishes(String name, String info, String price) {

        this.name = name;
        this.info = info;
        this.price = price;



    }


    public static void restList1() {


        RestMenu.listDish.clear();
        RestMenu.listDish.add(new Dishes("Padgrapao", "Röd chili, basilika, lök och vitlök" +
                " ( Välj mellan biff, fläsk, kyckling, räkor, skaldjur eller Tofu.)", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padnamprikpao", "chilipaste i olja, paprika, lök, basilika " +
                "(Välj mellan kyckling, fläskfilé, biff, räkor, tofu eller skaldjur)", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padped", "Chili, limeblad, paprika och chilipasta i olja " +
                "Välj mellan biff, fläsk, kyckling, räkor, skaldjur och Tofu.", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Panäng", "Panängcurry, kokosmjölk, limeblad och chili " +
                "(Välj mellan kyckling, fläskfilé, biff, räkor, tofu eller skaldjur)", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padthai Gai", "Stekta nudlar med kyckling, jordnötter och böngroddar", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padthai Gong", "Stekta nudlar med räkor, jordnötter, böngroddar", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Biff med currysås", "", "99:-", 99));
        RestMenu.listDish.add(new Dishes("Kyckling med rödcurrysås", "", "99:-", 99));
        RestMenu.listDish.add(new Dishes("Vegetariska vårrullar", "", "50:-", 50));


    }

    public static void restList2() {
        RestMenu.listDish.clear();
        RestMenu.listDish.add(new Dishes("DeliVery Cheezy beef mål.", "En saftig burgare gjord på svenskt nötkött, cheddarost, ketchup, gul lök (sweet onion), smältost, majonnäs, allt omslutet av ett gyllenrostat sesambröd. En del ur vår speciellt framtagna" +
                " ”Made för Delivery”- meny. En burgare optimerad för hemleverans, erbjuds exklusivt i våra deliverykanaler. 4,7kg Co2e.", "115 kr", 115));
        RestMenu.listDish.add(new Dishes("Chili Cheese", "Avsluta din måltid med vår favorit - Chili cheese." +
                " Frasig och het, friterad cheddarsmältost med jalapeños. 0,7kg Co2e.", "27 kr", 27));
        RestMenu.listDish.add(new Dishes("Cheeseburgare", "En burgare gjord på svenskt nötkött med krispig isbergssallad, cheddarsmältost och originaldressing - allt omslutet av ett gyllenrostat sesambröd." +
                " 1kg Co2e", "18 kr", 18));
        RestMenu.listDish.add(new Dishes("Chicken Wings", "Härligt frasiga och smakrika kycklingvingar gjorda på svensk kyckling." +
                " 0,2kg Co2e.", "30 kr", 30));
        RestMenu.listDish.add(new Dishes("Dubbel Frisco mål", "En dubbelburgare av svenskt nötkött, cheddarsmältost, bacon, gul lök (Sweet Onion), tomat, isbergssallad och originaldressing – " +
                "allt omslutet av ett gyllenrostat friscobröd. Serveras med dryck och tillbehör. 4,6kg Co2e.", "119 kr", 119));
        RestMenu.listDish.add(new Dishes("Cheese 'n' Bacon mål", "En saftig burgare av svenskt nötkött på rostat sesambröd med originaldressing, äppelträrökt bacon, sallad, gul lök (sweet onion), tomat och cheddarsmältost." +
                " 4,4kg Co2e.", "107 kr", 107));
        RestMenu.listDish.add(new Dishes("Triple Cheese mål", "En saftig burgare av svenskt nötkött på rostat Friscobröd med creoledressing, cheddarsmältost, emmentalerost, pepper jack-ost, gul lök (sweet onion) och saltgurka." +
                " Serveras med dryck och tillbehör. 4,5kg Co2e.", "107 kr", 107));
        RestMenu.listDish.add(new Dishes("Cheese 'n' Bacon mål", "En saftig burgare av svenskt nötkött på rostat sesambröd med originaldressing, äppelträrökt bacon, sallad, gul lök (sweet onion)," +
                " tomat och cheddarsmältost. 4,4kg Co2e.", "107 kr", 107));
        RestMenu.listDish.add(new Dishes("Crispy nuggets mål 9 bitar", "Frasiga panerade växtbaserade nuggets (med en bas av sojaprotein)" +
                " kommer med valfri dipsås. 0,6kg Co2e.", "99 kr", 99));


    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getPrice() {
        return price;
    }

    public int getPrices() {

        return prices;
    }
}
