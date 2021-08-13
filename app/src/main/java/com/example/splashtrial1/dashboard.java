package com.example.splashtrial1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class dashboard extends AppCompatActivity {
    private FirebaseDatabase database ;
    private DatabaseReference mReference ;
    private DatabaseReference childReference ;
    String customerid;
    FirebaseAuth auth;
    private Context context;
    TextView textView,sharebt;


    TextView t,namee,emaile;
    ProgressBar progressBar;
    CardView cd,read,write;
    ProgressDialog dialog;


    private final int ID_HOME=1;
    private final int ID_MESSAGE=2;
    private final int ID_NOTIFICATION=3;
    private final int ID_ACCOUNT=4;
    CardView cardView,cardView1;
    public  void samplevideo(View view){
        startActivity(new Intent(dashboard.this,samplevideo.class));
    }
    public  void tr(View view){
        startActivity(new Intent(dashboard.this,trialpage.class));
    }
    public  void teacher(View view){
        startActivity(new Intent(dashboard.this,teacherlist.class));
    }
    public  void hit(View view){
        startActivity(new Intent(dashboard.this,seallcategorypage.class));
    }
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();
        textView=findViewById(R.id.hel);
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(dashboard.this);
        String username = preferences1.getString("username", "");
        if(!username.equalsIgnoreCase(""))
        {

            textView.setText("Hello "+username);

        }

        sharebt=findViewById(R.id.share);
        //share button(app download)
        sharebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Intent.ACTION_SEND);
                i.setType("text/plaintext");
                String[] array = {"me@gmail.com","he@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL,array);
                i.putExtra(Intent.EXTRA_SUBJECT,"Download VisionMile and get access to all services");
                i.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/file/d/19dtdd_aKE-Y_dUjkFDVxyKeA23izEbVC/view?usp=sharing");
                if(i.resolveActivity(getPackageManager())!= null){
                    startActivity(i);
                }

            }
        });
        //share button


        dialog = new ProgressDialog(this);
        dialog.setMessage("you will be signout from the app");
        dialog.setTitle("Sign out");
        progressBar=findViewById(R.id.pg);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        cardView=findViewById(R.id.cd1);
        cardView1=findViewById(R.id.toffle);
        cd=findViewById(R.id.ab);
        t=findViewById(R.id.rep);
        read=findViewById(R.id.readi);
        write=findViewById(R.id.writei);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),videoread.class));
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),videowrite.class));
            }
        });
       cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(dashboard.this);
                String name = preferences.getString("quiz", "");
                if(!name.equalsIgnoreCase(""))
                {
                    int c = Integer.parseInt(name.toString());
                    c=c*13;
                    t.setText(c+"% Complete");
                    progressBar.setProgress(c);

                }
            }
        });
        //for vaaccessprogress


      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("quiz", "");
        if(!name.equalsIgnoreCase(""))
        {
            int c = Integer.parseInt(name.toString());
            c=c*13;
            t.setText(c+"% Complete");
            progressBar.setProgress(c);

        }
        //toffleReference = mReference.child("Customers").child(customerid).child("toffle");

       /* vd=findViewById(R.id.read);
        vd1=findViewById(R.id.write);
        vd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,videoread.class));
            }
        });
        vd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,videowrite.class));
            }
        });*/
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,accesscoursetoffle.class));
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,accesscourse.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent intent = new Intent(dashboard.this, dashboard.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_aboutus:

                        Intent intent1 = new Intent(dashboard.this, aboutus.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_Policy:

                        Intent intent2 = new Intent(dashboard.this, termsandcondition.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_share:
                        Intent i= new Intent(Intent.ACTION_SEND);
                        i.setType("text/plaintext");
                        String[] array = {"me@gmail.com","he@gmail.com"};
                        i.putExtra(Intent.EXTRA_EMAIL,array);
                        i.putExtra(Intent.EXTRA_SUBJECT,"Download VisionMile and get access to all services");
                        i.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/file/d/19dtdd_aKE-Y_dUjkFDVxyKeA23izEbVC/view?usp=sharing");
                        if(i.resolveActivity(getPackageManager())!= null){
                            startActivity(i);
                        }

                        break;
                    case R.id.page2:

                        dialog.show();
                        final Timer timer=new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                timer.cancel();
                                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("remember","false");
                                editor.apply();
                                finish();
                                startActivity(new Intent(getApplicationContext(),login_activity.class));
                            }
                        },2000);
                        break;
                    case R.id.page1:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://karanjc.github.io/visionmile.github.io/"));
                        startActivity(browserIntent);
                        break;






//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;


                }
                return false;
            }
        });
        View header = navigationView.getHeaderView(0);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),userprofile.class));
            }
        });
        // namee=header.findViewById(R.id.nameu);
        //emaile=header.findViewById(R.id.emailu);*/
        //imageView=findViewById(R.id.imageView);
        //BOTTOM NAVIGATION
        //TextView selected_page = findViewById(R.id.selected_page);
        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MESSAGE,R.drawable.ic_baseline_message_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION,R.drawable.ic_baseline_notification_important_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT,R.drawable.ic_baseline_account_balance_24));

       bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(dashboard.this,"clicked item : "+ item.getId(),Toast.LENGTH_SHORT).show();

            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                String name;
                switch (item.getId()){
                    case ID_HOME:name = "Home";
                        break;
                    case ID_MESSAGE:name = "";
                        startActivity(new Intent(dashboard.this,courselist.class));

                        break;

                    case ID_NOTIFICATION:name = "NOTIFICATION";
                        startActivity(new Intent(dashboard.this,testdashboard.class));


                        break;
                    case ID_ACCOUNT:name = "ACCOUNT";
                    startActivity(new Intent(dashboard.this,userprofile.class));
                        break;
                    default:name = "";
                }
               // selected_page.setText(getString(R.string.main_page_selected,name));
            }
        });
        bottomNavigation.setCount(ID_NOTIFICATION,"4");
        bottomNavigation.show(ID_HOME,true);




    }
    //po
    //po

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
    //option menu code
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(dashboard.this,settingactivity.class));
                return true;
        }
            return super.onOptionsItemSelected(item);
        }
        //progress




}