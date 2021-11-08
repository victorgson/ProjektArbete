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


    public static void Kt() {


        RestMenu.listDish.clear();
        RestMenu.listDish.add(new Dishes("Vegetariska vårrullar", "", "50:-", 50));
        RestMenu.listDish.add(new Dishes("Padgrapao", "Röd chili, basilika, lök och vitlök" +
                " ( Välj mellan biff, fläsk, kyckling, räkor, skaldjur eller Tofu.)", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padnamprikpao", "chilipaste i olja, paprika, lök, basilika " +
                "(Välj mellan kyckling, fläskfilé, biff, räkor, tofu eller skaldjur)", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padped", "Chili, limeblad, paprika och chilipasta i olja " +
                "(Välj mellan biff, fläsk, kyckling, räkor, skaldjur och Tofu.)", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Panäng", "Panängcurry, kokosmjölk, limeblad och chili " +
                "(Välj mellan kyckling, fläskfilé, biff, räkor, tofu eller skaldjur)", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padthai Gai", "Stekta nudlar med kyckling, jordnötter och böngroddar", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Padthai Gong", "Stekta nudlar med räkor, jordnötter, böngroddar", "109:-", 109));
        RestMenu.listDish.add(new Dishes("Biff med currysås", "", "99:-", 99));
        RestMenu.listDish.add(new Dishes("Kyckling med rödcurrysås", "", "99:-", 99));



    }

    public static void Max() {
        RestMenu.listDish.clear();
        RestMenu.listDish.add(new Dishes("DeliVery Cheezy beef mål.", "En saftig burgare gjord på svenskt nötkött, cheddarost, ketchup, gul lök (sweet onion), smältost, majonnäs, allt omslutet av ett gyllenrostat sesambröd. En del ur vår speciellt framtagna" +
                " ”Made för Delivery”- meny. En burgare optimerad för hemleverans, erbjuds exklusivt i våra deliverykanaler. 4,7kg Co2e.", "115 :-", 115));
        RestMenu.listDish.add(new Dishes("Chili Cheese", "Avsluta din måltid med vår favorit - Chili cheese." +
                " Frasig och het, friterad cheddarsmältost med jalapeños. 0,7kg Co2e.", " 27 :-", 27));
        RestMenu.listDish.add(new Dishes("Cheeseburgare", "En burgare gjord på svenskt nötkött med krispig isbergssallad, cheddarsmältost och originaldressing - allt omslutet av ett gyllenrostat sesambröd." +
                " 1kg Co2e", "18 :-", 18));
        RestMenu.listDish.add(new Dishes("Chicken Wings", "Härligt frasiga och smakrika kycklingvingar gjorda på svensk kyckling." +
                " 0,2kg Co2e.", "30 :-", 30));
        RestMenu.listDish.add(new Dishes("Dubbel Frisco mål", "En dubbelburgare av svenskt nötkött, cheddarsmältost, bacon, gul lök (Sweet Onion), tomat, isbergssallad och originaldressing – " +
                "allt omslutet av ett gyllenrostat friscobröd. Serveras med dryck och tillbehör. 4,6kg Co2e.", "119 :-", 119));
        RestMenu.listDish.add(new Dishes("Cheese 'n' Bacon mål", "En saftig burgare av svenskt nötkött på rostat sesambröd med originaldressing, äppelträrökt bacon, sallad, gul lök (sweet onion), tomat och cheddarsmältost." +
                " 4,4kg Co2e.", "107 :-", 107));
        RestMenu.listDish.add(new Dishes("Triple Cheese mål", "En saftig burgare av svenskt nötkött på rostat Friscobröd med creoledressing, cheddarsmältost, emmentalerost, pepper jack-ost, gul lök (sweet onion) och saltgurka." +
                " Serveras med dryck och tillbehör. 4,5kg Co2e.", "107 :-", 107));
        RestMenu.listDish.add(new Dishes("Cheese 'n' Bacon mål", "En saftig burgare av svenskt nötkött på rostat sesambröd med originaldressing, äppelträrökt bacon, sallad, gul lök (sweet onion)," +
                " tomat och cheddarsmältost. 4,4kg Co2e.", "107 :-", 107));
        RestMenu.listDish.add(new Dishes("Crispy nuggets mål 9 bitar", "Frasiga panerade växtbaserade nuggets (med en bas av sojaprotein)" +
                " kommer med valfri dipsås. 0,6kg Co2e.", "99 :-", 99));


    }
    public static void Bombay() {
        RestMenu.listDish.clear();
        RestMenu.listDish.add(new Dishes("CHICKEN KORMA", "Mild kycklingfilé tillagad med en smakrik gräddsås, nötter och russin.\n" +
                "Chicken fillet, made in a tasty cream sauce with nuts and raisins (mild)", "135 :-", 135));
        RestMenu.listDish.add(new Dishes("MURGH BALTI", "Kycklingfilé marinerad i vitlök och grön chili, tillagad med lök, paprika och champinjoner i en rödvinsås.\n" +
                "Chicken fillet marinated in garlic and green chili, prepared with onions, peppers and mushrooms in a red wine sauce.", "134 :-", 134));
        RestMenu.listDish.add(new Dishes("CHICKEN VINDALOO", "Stark Kyckling gryta med doft av färska curry bald bräserat i vinäger farinsocker, nejlika och kanel.\n" +
                "Strong Chicken stew with scent of curry leaves braised in vinegar brown sugar, carnation and cinnamon.", "134 :-", 134));
        RestMenu.listDish.add(new Dishes("CHICKEN KALI MIRCH", "Kycklingfilé tillagad i kokosmjölk, svartpeppar, lök och tomat.\n" +
                "Chicken fillet cooked in coconut milk, black pepper, onion and tomato.", "134 :-", 134));
        RestMenu.listDish.add(new Dishes("CHICKEN TIKKA MASALA", "Grillad kycklingfilé med färska tomtat och råstad paprika i en krämig sås.\n" +
                "Grilled chicken fillet with fresh tomato and roasted paprika in a creamy sauce.", "134 :-", 134));
        RestMenu.listDish.add(new Dishes("BUTTER CHICKEN", "Grillad kycklingfilé, serveras med en aromatisk gräddsås av tomat, ost, cashewnötter och smör.\n" +
                "Grilled chicken fillet, made in an aromatic sauce of tomatoes, cream, cheese, cashews and butter.", "134 :-", 134));
        RestMenu.listDish.add(new Dishes("CHICKEN CHAKORI", "Kycklingfilé med spenat, ingefära, vitlök och garam masala.\n" +
                "Chicken fillet made with spinach, ginger, garlic and garam masala.", " 134 :-", 134));
        RestMenu.listDish.add(new Dishes("MURGH SHARABI", "Grillad kycklingfilé med ingefära, vitlök, grön chili, linser och en kryddstark cognacsås.\n" +
                "Grilled chicken fillet in a spicy cognac sauce with ginger, garlic, green chili and lentils.", "134 :-", 134));
        RestMenu.listDish.add(new Dishes("MURGH MHETI MALAI ", "Kycklingfilé tillagad med bockhornsklöver, tomat, kardemumma, grädde.\n" +
                "Chicken fillet cooked with fenugreek, tomato, cardamom, cream.", " 135 :-", 135));
        RestMenu.listDish.add(new Dishes("MURGH MADRAS ", "Kyckling gryta från södra indien tillagad med senapsfrön, curryblad, grön chili & koksmjölk.\n" +
                "Chicken stew from southern India cooked with mustard seeds, curry leaf green chili and coconut milk.", " 135 :-", 135));
        RestMenu.listDish.add(new Dishes("JALFREZI CHICKEN ", "Kryddig gryta med curry, tomat, lök, zucchini, paprika och koriander.\n" +
                "Spicy stew with curry, tomato, onion, zucchini, peppers and coriander.", " 135 :-", 135));


    }
    public static void Kfc() {
        RestMenu.listDish.clear();
        RestMenu.listDish.add(new Dishes("ZINGER BURGER", "Vi tillagar vår Zinger Burger genom att först marinera en kycklingfilé i en starkare marinad för att sedan panera den i en speciell panering som ger filéen en extra krispig yta. Efter tillagning lägger vi Zinger-filéen i ett sesamfrö-garnerat bröd tillsammans med fräsch romansallad, nyskuren tomat och mayonnaise. Njut av burgaren som den är eller lägg till en meny med tillbehör och dryck.",
                " 57 :-", 57));
        RestMenu.listDish.add(new Dishes("DBL ORIGINAL BBQ", "En Dbl Original BBQ består utav ca 166 gram kycklingfilé i Original Recipe-panering, ost, lök, romansallad, vår egen BBQ-sås och pepparmayonnaise. I menyn ingår 1 tillbehör och 1 dryck.",
                " 85 :-", 85));
        RestMenu.listDish.add(new Dishes("HALLOUMI BURGER", "Prova vår Halloumi Burger med sallad, lök, tomat och pepparmajonnäs! Ta burgaren som den är eller lägg till en meny med tillbehör och dryck."
                , " 39 :-", 39));
        RestMenu.listDish.add(new Dishes("ORIGINAL RECIPE BUCKET", "Original Recipe Bucket började serveras redan år 1957 och är KFC:s första Bucket! Efter att vi har panerat kycklingen i vår kryddmix med 11 olika örter och kryddor tillagar vi den långsamt under tryck så att den får en krispig yta och en härligt saftig insida!"
                , " 109 :-", 109));
        RestMenu.listDish.add(new Dishes("SMALL MIX BUCKET", "Vår Small Mix Bucket innehåller en blandning av våra kycklingprodukter och passar därför bra för de som vill testa flera produkter på samma gång. Small Mix innehåller:\n" +
                "Kyckling: 4 Crispy Strips, 6 Hot Wings och 2 Original Recipe. Tillbehör: 2 drycker, 2 valfria tillbehör och 2 dippsåser.", "199 :-", 199));
        RestMenu.listDish.add(new Dishes("BIG MIX BUCKET", "En Big Mix Bucket innehåller alla våra olika kycklingprodukter och passar ett större sällskap på fyra till fem personer.", "389 :-", 389));
        RestMenu.listDish.add(new Dishes("BONELESS BUCKET", "En Boneless Bucket innehåller helt benfria kycklingbitar och är därför perfekt att dippa!", "199 :-", 199));
        RestMenu.listDish.add(new Dishes("CRISPY STRIPS BUCKET", "Crispy Strips Bucket innehåller krispigt panerade innerfiléer.", "108 :-", 108));
        RestMenu.listDish.add(new Dishes("POPCORN CHICKEN", "Våra Popcorn Chicken är gjorda av små panerade kycklingfiléer i samma storlek och nästan samma form som popcorn och är perfekta för att dippa i såser eftersom de är helt benfria. I din meny ingår ca 30 st Popcorn Chicken med tillbehör och dryck."
                , " 99 :-", 99));


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
