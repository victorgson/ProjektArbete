package com.example.projektarbete;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestMenu extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    String restID;
    static List<Dishes> listDish;
    static ImageView loga;
    static TextView rtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_menu);
        getSupportActionBar().hide();
        init();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            ((Window) window).addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.support_bar));
        }

        listDish = new ArrayList<>();
        RestLoga rLoga = new RestLoga();
        RestInfo rInfo = new RestInfo();


        loga = (ImageView) findViewById(R.id.restBild);
        rtInfo = (TextView) findViewById(R.id.restInfo);
        ListView listView = (ListView) findViewById(R.id.menulista);



        if (getIntent().getStringExtra("Rest1").equals("KanyasThai")) {
            Kt();
            rLoga.setLogaKt(loga);
            rInfo.setRTKt(rtInfo);
        } else if (getIntent().getStringExtra("Rest1").equals("Max")) {
            Max();
            rLoga.setLogaMacD(loga);
            rInfo.setRTMax(rtInfo);

        } else if (getIntent().getStringExtra("Rest1").equals("Bombay")) {
            Bombay();
            rLoga.setLogaBombay(loga);
            rInfo.setRTBombay(rtInfo);
        } else if (getIntent().getStringExtra("Rest1").equals("KFC")) {
            Kfc();
            rLoga.setLogaKfc(loga);
            rInfo.setRTKfc(rtInfo);
        }


        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, listDish);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent();
                intent.putExtra("Name", listDish.get(position).getName());
                intent.putExtra("Info", listDish.get(position).getInfo());
                intent.putExtra("Price", listDish.get(position).getPrice());
                intent.putExtra("DishPrice", listDish.get(position).getPrices());
                intent.putExtra("Rest1", getIntent().getStringExtra("Rest1"));


                intent.setClass(RestMenu.this, BuyActivity.class);
                startActivity(intent);

            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.order:
                        Intent intent=new Intent();
                        intent.putExtra("Timer","NORUN");
                        intent.setClass(RestMenu.this,TimerActivity.class);
                        startActivity(intent);
                        //startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                }


                return false;
            }
        });
    }

    public void writeNewDish(String dishName, String dishInfo, String dishPrice, int price){
        // Creates new object of user
        Dishes dish = new Dishes(dishName, dishInfo, dishPrice, price);
        try {
            mDatabase.child("Bombay").child("Dishes").push().setValue(dish);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void addDishEventListener(DatabaseReference mPostReference) {
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            private static final String TAG = "TEST";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //Post post = dataSnapshot.getValue(Post.class);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Dishes d = new Dishes();
                    System.out.println(ds.getValue());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]
    }


    void init(){

        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        ref = mDatabase.child("restaurants");
        mAuth = FirebaseAuth.getInstance();
        restID = "Bombay";
        addDishEventListener(ref);
        System.out.println("KÖR!");
        writeNewDish("Köttbullar","med ost","125 :-",125);

    }



    public ArrayList<Dishes> KtMenu(){

        ArrayList<Dishes> kt = new ArrayList<Dishes>();
        Dishes one = new Dishes("Vegetariska vårrullar", "", "50:-", 50);
        kt.add(one);


        return kt;
    }

    public void FillList(){
        listDish.clear();
        listDish.addAll(KtMenu());


    }

    public void Kt() {


        listDish.clear();
        listDish.add(new Dishes("Vegetariska vårrullar", "", "50:-", 50));
        listDish.add(new Dishes("Padgrapao", "Röd chili, basilika, lök och vitlök" +
                " ( Välj mellan biff, fläsk, kyckling, räkor, skaldjur eller Tofu.)", "109:-", 109));
        listDish.add(new Dishes("Padnamprikpao", "chilipaste i olja, paprika, lök, basilika " +
                "(Välj mellan kyckling, fläskfilé, biff, räkor, tofu eller skaldjur)", "109:-", 109));
        listDish.add(new Dishes("Padped", "Chili, limeblad, paprika och chilipasta i olja " +
                "(Välj mellan biff, fläsk, kyckling, räkor, skaldjur och Tofu.)", "109:-", 109));
        listDish.add(new Dishes("Panäng", "Panängcurry, kokosmjölk, limeblad och chili " +
                "(Välj mellan kyckling, fläskfilé, biff, räkor, tofu eller skaldjur)", "109:-", 109));
        listDish.add(new Dishes("Padthai Gai", "Stekta nudlar med kyckling, jordnötter och böngroddar", "109:-", 109));
        listDish.add(new Dishes("Padthai Gong", "Stekta nudlar med räkor, jordnötter, böngroddar", "109:-", 109));
        listDish.add(new Dishes("Biff med currysås", "", "99:-", 99));
        listDish.add(new Dishes("Kyckling med rödcurrysås", "", "99:-", 99));



    }

    public void Max() {
        listDish.clear();
        listDish.add(new Dishes("DeliVery Cheezy beef mål.", "En saftig burgare gjord på svenskt nötkött, cheddarost, ketchup, gul lök (sweet onion), smältost, majonnäs, allt omslutet av ett gyllenrostat sesambröd. En del ur vår speciellt framtagna" +
                " ”Made för Delivery”- meny. En burgare optimerad för hemleverans, erbjuds exklusivt i våra deliverykanaler. 4,7kg Co2e.", "115 :-", 115));
        listDish.add(new Dishes("Chili Cheese", "Avsluta din måltid med vår favorit - Chili cheese." +
                " Frasig och het, friterad cheddarsmältost med jalapeños. 0,7kg Co2e.", " 27 :-", 27));
        listDish.add(new Dishes("Cheeseburgare", "En burgare gjord på svenskt nötkött med krispig isbergssallad, cheddarsmältost och originaldressing - allt omslutet av ett gyllenrostat sesambröd." +
                " 1kg Co2e", "18 :-", 18));
        listDish.add(new Dishes("Chicken Wings", "Härligt frasiga och smakrika kycklingvingar gjorda på svensk kyckling." +
                " 0,2kg Co2e.", "30 :-", 30));
        listDish.add(new Dishes("Dubbel Frisco mål", "En dubbelburgare av svenskt nötkött, cheddarsmältost, bacon, gul lök (Sweet Onion), tomat, isbergssallad och originaldressing – " +
                "allt omslutet av ett gyllenrostat friscobröd. Serveras med dryck och tillbehör. 4,6kg Co2e.", "119 :-", 119));
        listDish.add(new Dishes("Cheese 'n' Bacon mål", "En saftig burgare av svenskt nötkött på rostat sesambröd med originaldressing, äppelträrökt bacon, sallad, gul lök (sweet onion), tomat och cheddarsmältost." +
                " 4,4kg Co2e.", "107 :-", 107));
        listDish.add(new Dishes("Triple Cheese mål", "En saftig burgare av svenskt nötkött på rostat Friscobröd med creoledressing, cheddarsmältost, emmentalerost, pepper jack-ost, gul lök (sweet onion) och saltgurka." +
                " Serveras med dryck och tillbehör. 4,5kg Co2e.", "107 :-", 107));
        listDish.add(new Dishes("Cheese 'n' Bacon mål", "En saftig burgare av svenskt nötkött på rostat sesambröd med originaldressing, äppelträrökt bacon, sallad, gul lök (sweet onion)," +
                " tomat och cheddarsmältost. 4,4kg Co2e.", "107 :-", 107));
        listDish.add(new Dishes("Crispy nuggets mål 9 bitar", "Frasiga panerade växtbaserade nuggets (med en bas av sojaprotein)" +
                " kommer med valfri dipsås. 0,6kg Co2e.", "99 :-", 99));


    }
    public void Bombay() {
        listDish.clear();
        listDish.add(new Dishes("CHICKEN KORMA", "Mild kycklingfilé tillagad med en smakrik gräddsås, nötter och russin.\n" +
                "Chicken fillet, made in a tasty cream sauce with nuts and raisins (mild)", "135 :-", 135));
        listDish.add(new Dishes("MURGH BALTI", "Kycklingfilé marinerad i vitlök och grön chili, tillagad med lök, paprika och champinjoner i en rödvinsås.\n" +
                "Chicken fillet marinated in garlic and green chili, prepared with onions, peppers and mushrooms in a red wine sauce.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN VINDALOO", "Stark Kyckling gryta med doft av färska curry bald bräserat i vinäger farinsocker, nejlika och kanel.\n" +
                "Strong Chicken stew with scent of curry leaves braised in vinegar brown sugar, carnation and cinnamon.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN KALI MIRCH", "Kycklingfilé tillagad i kokosmjölk, svartpeppar, lök och tomat.\n" +
                "Chicken fillet cooked in coconut milk, black pepper, onion and tomato.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN TIKKA MASALA", "Grillad kycklingfilé med färska tomtat och råstad paprika i en krämig sås.\n" +
                "Grilled chicken fillet with fresh tomato and roasted paprika in a creamy sauce.", "134 :-", 134));
        listDish.add(new Dishes("BUTTER CHICKEN", "Grillad kycklingfilé, serveras med en aromatisk gräddsås av tomat, ost, cashewnötter och smör.\n" +
                "Grilled chicken fillet, made in an aromatic sauce of tomatoes, cream, cheese, cashews and butter.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN CHAKORI", "Kycklingfilé med spenat, ingefära, vitlök och garam masala.\n" +
                "Chicken fillet made with spinach, ginger, garlic and garam masala.", " 134 :-", 134));
        listDish.add(new Dishes("MURGH SHARABI", "Grillad kycklingfilé med ingefära, vitlök, grön chili, linser och en kryddstark cognacsås.\n" +
                "Grilled chicken fillet in a spicy cognac sauce with ginger, garlic, green chili and lentils.", "134 :-", 134));
        listDish.add(new Dishes("MURGH MHETI MALAI ", "Kycklingfilé tillagad med bockhornsklöver, tomat, kardemumma, grädde.\n" +
                "Chicken fillet cooked with fenugreek, tomato, cardamom, cream.", " 135 :-", 135));
        listDish.add(new Dishes("MURGH MADRAS ", "Kyckling gryta från södra indien tillagad med senapsfrön, curryblad, grön chili & koksmjölk.\n" +
                "Chicken stew from southern India cooked with mustard seeds, curry leaf green chili and coconut milk.", " 135 :-", 135));
        listDish.add(new Dishes("JALFREZI CHICKEN ", "Kryddig gryta med curry, tomat, lök, zucchini, paprika och koriander.\n" +
                "Spicy stew with curry, tomato, onion, zucchini, peppers and coriander.", " 135 :-", 135));


    }
    public void Kfc() {
        listDish.clear();
        listDish.add(new Dishes("ZINGER BURGER", "Vi tillagar vår Zinger Burger genom att först marinera en kycklingfilé i en starkare marinad för att sedan panera den i en speciell panering som ger filéen en extra krispig yta. Efter tillagning lägger vi Zinger-filéen i ett sesamfrö-garnerat bröd tillsammans med fräsch romansallad, nyskuren tomat och mayonnaise. Njut av burgaren som den är eller lägg till en meny med tillbehör och dryck.",
                " 57 :-", 57));
        listDish.add(new Dishes("DBL ORIGINAL BBQ", "En Dbl Original BBQ består utav ca 166 gram kycklingfilé i Original Recipe-panering, ost, lök, romansallad, vår egen BBQ-sås och pepparmayonnaise. I menyn ingår 1 tillbehör och 1 dryck.",
                " 85 :-", 85));
        listDish.add(new Dishes("HALLOUMI BURGER", "Prova vår Halloumi Burger med sallad, lök, tomat och pepparmajonnäs! Ta burgaren som den är eller lägg till en meny med tillbehör och dryck."
                , " 39 :-", 39));
        listDish.add(new Dishes("ORIGINAL RECIPE BUCKET", "Original Recipe Bucket började serveras redan år 1957 och är KFC:s första Bucket! Efter att vi har panerat kycklingen i vår kryddmix med 11 olika örter och kryddor tillagar vi den långsamt under tryck så att den får en krispig yta och en härligt saftig insida!"
                , " 109 :-", 109));
        listDish.add(new Dishes("SMALL MIX BUCKET", "Vår Small Mix Bucket innehåller en blandning av våra kycklingprodukter och passar därför bra för de som vill testa flera produkter på samma gång. Small Mix innehåller:\n" +
                "Kyckling: 4 Crispy Strips, 6 Hot Wings och 2 Original Recipe. Tillbehör: 2 drycker, 2 valfria tillbehör och 2 dippsåser.", "199 :-", 199));
        listDish.add(new Dishes("BIG MIX BUCKET", "En Big Mix Bucket innehåller alla våra olika kycklingprodukter och passar ett större sällskap på fyra till fem personer.", "389 :-", 389));
        listDish.add(new Dishes("BONELESS BUCKET", "En Boneless Bucket innehåller helt benfria kycklingbitar och är därför perfekt att dippa!", "199 :-", 199));
        listDish.add(new Dishes("CRISPY STRIPS BUCKET", "Crispy Strips Bucket innehåller krispigt panerade innerfiléer.", "108 :-", 108));
        listDish.add(new Dishes("POPCORN CHICKEN", "Våra Popcorn Chicken är gjorda av små panerade kycklingfiléer i samma storlek och nästan samma form som popcorn och är perfekta för att dippa i såser eftersom de är helt benfria. I din meny ingår ca 30 st Popcorn Chicken med tillbehör och dryck."
                , " 99 :-", 99));


    }

}
