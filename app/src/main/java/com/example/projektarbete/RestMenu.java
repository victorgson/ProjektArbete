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
            Dishes.Kt();
            rLoga.setLogaKt(loga);
            rInfo.setRTKt(rtInfo);
        } else if (getIntent().getStringExtra("Rest1").equals("Max")) {
            Dishes.Max();
            rLoga.setLogaMacD(loga);
            rInfo.setRTMax(rtInfo);

        } else if (getIntent().getStringExtra("Rest1").equals("Bombay")) {
            Dishes.Bombay();
            rLoga.setLogaBombay(loga);
            rInfo.setRTBombay(rtInfo);
        } else if (getIntent().getStringExtra("Rest1").equals("KFC")) {
            Dishes.Kfc();
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
                        startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                        overridePendingTransition(0, 0);
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
        System.out.println("K??R!");
        writeNewDish("K??ttbullar","med ost","125 :-",125);

    }



    public ArrayList<Dishes> KtMenu(){

        ArrayList<Dishes> kt = new ArrayList<Dishes>();
        Dishes one = new Dishes("Vegetariska v??rrullar", "", "50:-", 50);
        kt.add(one);


        return kt;
    }

    public void FillList(){
        listDish.clear();
        listDish.addAll(KtMenu());


    }

    public void Kt() {


        listDish.clear();
        listDish.add(new Dishes("Vegetariska v??rrullar", "", "50:-", 50));
        listDish.add(new Dishes("Padgrapao", "R??d chili, basilika, l??k och vitl??k" +
                " ( V??lj mellan biff, fl??sk, kyckling, r??kor, skaldjur eller Tofu.)", "109:-", 109));
        listDish.add(new Dishes("Padnamprikpao", "chilipaste i olja, paprika, l??k, basilika " +
                "(V??lj mellan kyckling, fl??skfil??, biff, r??kor, tofu eller skaldjur)", "109:-", 109));
        listDish.add(new Dishes("Padped", "Chili, limeblad, paprika och chilipasta i olja " +
                "(V??lj mellan biff, fl??sk, kyckling, r??kor, skaldjur och Tofu.)", "109:-", 109));
        listDish.add(new Dishes("Pan??ng", "Pan??ngcurry, kokosmj??lk, limeblad och chili " +
                "(V??lj mellan kyckling, fl??skfil??, biff, r??kor, tofu eller skaldjur)", "109:-", 109));
        listDish.add(new Dishes("Padthai Gai", "Stekta nudlar med kyckling, jordn??tter och b??ngroddar", "109:-", 109));
        listDish.add(new Dishes("Padthai Gong", "Stekta nudlar med r??kor, jordn??tter, b??ngroddar", "109:-", 109));
        listDish.add(new Dishes("Biff med currys??s", "", "99:-", 99));
        listDish.add(new Dishes("Kyckling med r??dcurrys??s", "", "99:-", 99));



    }

    public void Max() {
        listDish.clear();
        listDish.add(new Dishes("DeliVery Cheezy beef m??l.", "En saftig burgare gjord p?? svenskt n??tk??tt, cheddarost, ketchup, gul l??k (sweet onion), sm??ltost, majonn??s, allt omslutet av ett gyllenrostat sesambr??d. En del ur v??r speciellt framtagna" +
                " ???Made f??r Delivery???- meny. En burgare optimerad f??r hemleverans, erbjuds exklusivt i v??ra deliverykanaler. 4,7kg Co2e.", "115 :-", 115));
        listDish.add(new Dishes("Chili Cheese", "Avsluta din m??ltid med v??r favorit - Chili cheese." +
                " Frasig och het, friterad cheddarsm??ltost med jalape??os. 0,7kg Co2e.", " 27 :-", 27));
        listDish.add(new Dishes("Cheeseburgare", "En burgare gjord p?? svenskt n??tk??tt med krispig isbergssallad, cheddarsm??ltost och originaldressing - allt omslutet av ett gyllenrostat sesambr??d." +
                " 1kg Co2e", "18 :-", 18));
        listDish.add(new Dishes("Chicken Wings", "H??rligt frasiga och smakrika kycklingvingar gjorda p?? svensk kyckling." +
                " 0,2kg Co2e.", "30 :-", 30));
        listDish.add(new Dishes("Dubbel Frisco m??l", "En dubbelburgare av svenskt n??tk??tt, cheddarsm??ltost, bacon, gul l??k (Sweet Onion), tomat, isbergssallad och originaldressing ??? " +
                "allt omslutet av ett gyllenrostat friscobr??d. Serveras med dryck och tillbeh??r. 4,6kg Co2e.", "119 :-", 119));
        listDish.add(new Dishes("Cheese 'n' Bacon m??l", "En saftig burgare av svenskt n??tk??tt p?? rostat sesambr??d med originaldressing, ??ppeltr??r??kt bacon, sallad, gul l??k (sweet onion), tomat och cheddarsm??ltost." +
                " 4,4kg Co2e.", "107 :-", 107));
        listDish.add(new Dishes("Triple Cheese m??l", "En saftig burgare av svenskt n??tk??tt p?? rostat Friscobr??d med creoledressing, cheddarsm??ltost, emmentalerost, pepper jack-ost, gul l??k (sweet onion) och saltgurka." +
                " Serveras med dryck och tillbeh??r. 4,5kg Co2e.", "107 :-", 107));
        listDish.add(new Dishes("Cheese 'n' Bacon m??l", "En saftig burgare av svenskt n??tk??tt p?? rostat sesambr??d med originaldressing, ??ppeltr??r??kt bacon, sallad, gul l??k (sweet onion)," +
                " tomat och cheddarsm??ltost. 4,4kg Co2e.", "107 :-", 107));
        listDish.add(new Dishes("Crispy nuggets m??l 9 bitar", "Frasiga panerade v??xtbaserade nuggets (med en bas av sojaprotein)" +
                " kommer med valfri dips??s. 0,6kg Co2e.", "99 :-", 99));


    }
    public void Bombay() {
        listDish.clear();
        listDish.add(new Dishes("CHICKEN KORMA", "Mild kycklingfil?? tillagad med en smakrik gr??dds??s, n??tter och russin.\n" +
                "Chicken fillet, made in a tasty cream sauce with nuts and raisins (mild)", "135 :-", 135));
        listDish.add(new Dishes("MURGH BALTI", "Kycklingfil?? marinerad i vitl??k och gr??n chili, tillagad med l??k, paprika och champinjoner i en r??dvins??s.\n" +
                "Chicken fillet marinated in garlic and green chili, prepared with onions, peppers and mushrooms in a red wine sauce.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN VINDALOO", "Stark Kyckling gryta med doft av f??rska curry bald br??serat i vin??ger farinsocker, nejlika och kanel.\n" +
                "Strong Chicken stew with scent of curry leaves braised in vinegar brown sugar, carnation and cinnamon.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN KALI MIRCH", "Kycklingfil?? tillagad i kokosmj??lk, svartpeppar, l??k och tomat.\n" +
                "Chicken fillet cooked in coconut milk, black pepper, onion and tomato.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN TIKKA MASALA", "Grillad kycklingfil?? med f??rska tomtat och r??stad paprika i en kr??mig s??s.\n" +
                "Grilled chicken fillet with fresh tomato and roasted paprika in a creamy sauce.", "134 :-", 134));
        listDish.add(new Dishes("BUTTER CHICKEN", "Grillad kycklingfil??, serveras med en aromatisk gr??dds??s av tomat, ost, cashewn??tter och sm??r.\n" +
                "Grilled chicken fillet, made in an aromatic sauce of tomatoes, cream, cheese, cashews and butter.", "134 :-", 134));
        listDish.add(new Dishes("CHICKEN CHAKORI", "Kycklingfil?? med spenat, ingef??ra, vitl??k och garam masala.\n" +
                "Chicken fillet made with spinach, ginger, garlic and garam masala.", " 134 :-", 134));
        listDish.add(new Dishes("MURGH SHARABI", "Grillad kycklingfil?? med ingef??ra, vitl??k, gr??n chili, linser och en kryddstark cognacs??s.\n" +
                "Grilled chicken fillet in a spicy cognac sauce with ginger, garlic, green chili and lentils.", "134 :-", 134));
        listDish.add(new Dishes("MURGH MHETI MALAI ", "Kycklingfil?? tillagad med bockhornskl??ver, tomat, kardemumma, gr??dde.\n" +
                "Chicken fillet cooked with fenugreek, tomato, cardamom, cream.", " 135 :-", 135));
        listDish.add(new Dishes("MURGH MADRAS ", "Kyckling gryta fr??n s??dra indien tillagad med senapsfr??n, curryblad, gr??n chili & koksmj??lk.\n" +
                "Chicken stew from southern India cooked with mustard seeds, curry leaf green chili and coconut milk.", " 135 :-", 135));
        listDish.add(new Dishes("JALFREZI CHICKEN ", "Kryddig gryta med curry, tomat, l??k, zucchini, paprika och koriander.\n" +
                "Spicy stew with curry, tomato, onion, zucchini, peppers and coriander.", " 135 :-", 135));


    }
    public void Kfc() {
        listDish.clear();
        listDish.add(new Dishes("ZINGER BURGER", "Vi tillagar v??r Zinger Burger genom att f??rst marinera en kycklingfil?? i en starkare marinad f??r att sedan panera den i en speciell panering som ger fil??en en extra krispig yta. Efter tillagning l??gger vi Zinger-fil??en i ett sesamfr??-garnerat br??d tillsammans med fr??sch romansallad, nyskuren tomat och mayonnaise. Njut av burgaren som den ??r eller l??gg till en meny med tillbeh??r och dryck.",
                " 57 :-", 57));
        listDish.add(new Dishes("DBL ORIGINAL BBQ", "En Dbl Original BBQ best??r utav ca 166 gram kycklingfil?? i Original Recipe-panering, ost, l??k, romansallad, v??r egen BBQ-s??s och pepparmayonnaise. I menyn ing??r 1 tillbeh??r och 1 dryck.",
                " 85 :-", 85));
        listDish.add(new Dishes("HALLOUMI BURGER", "Prova v??r Halloumi Burger med sallad, l??k, tomat och pepparmajonn??s! Ta burgaren som den ??r eller l??gg till en meny med tillbeh??r och dryck."
                , " 39 :-", 39));
        listDish.add(new Dishes("ORIGINAL RECIPE BUCKET", "Original Recipe Bucket b??rjade serveras redan ??r 1957 och ??r KFC:s f??rsta Bucket! Efter att vi har panerat kycklingen i v??r kryddmix med 11 olika ??rter och kryddor tillagar vi den l??ngsamt under tryck s?? att den f??r en krispig yta och en h??rligt saftig insida!"
                , " 109 :-", 109));
        listDish.add(new Dishes("SMALL MIX BUCKET", "V??r Small Mix Bucket inneh??ller en blandning av v??ra kycklingprodukter och passar d??rf??r bra f??r de som vill testa flera produkter p?? samma g??ng. Small Mix inneh??ller:\n" +
                "Kyckling: 4 Crispy Strips, 6 Hot Wings och 2 Original Recipe. Tillbeh??r: 2 drycker, 2 valfria tillbeh??r och 2 dipps??ser.", "199 :-", 199));
        listDish.add(new Dishes("BIG MIX BUCKET", "En Big Mix Bucket inneh??ller alla v??ra olika kycklingprodukter och passar ett st??rre s??llskap p?? fyra till fem personer.", "389 :-", 389));
        listDish.add(new Dishes("BONELESS BUCKET", "En Boneless Bucket inneh??ller helt benfria kycklingbitar och ??r d??rf??r perfekt att dippa!", "199 :-", 199));
        listDish.add(new Dishes("CRISPY STRIPS BUCKET", "Crispy Strips Bucket inneh??ller krispigt panerade innerfil??er.", "108 :-", 108));
        listDish.add(new Dishes("POPCORN CHICKEN", "V??ra Popcorn Chicken ??r gjorda av sm?? panerade kycklingfil??er i samma storlek och n??stan samma form som popcorn och ??r perfekta f??r att dippa i s??ser eftersom de ??r helt benfria. I din meny ing??r ca 30 st Popcorn Chicken med tillbeh??r och dryck."
                , " 99 :-", 99));


    }

}
