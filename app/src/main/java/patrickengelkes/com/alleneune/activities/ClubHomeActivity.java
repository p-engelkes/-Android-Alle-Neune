package patrickengelkes.com.alleneune.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import patrickengelkes.com.alleneune.Objects.Club;
import patrickengelkes.com.alleneune.R;

public class ClubHomeActivity extends Activity {

    protected TextView clubName;
    protected Button createEventButton;

    protected Intent clubIntent;
    protected Club club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_home);

        clubIntent = getIntent();
        club = clubIntent.getParcelableExtra("club");
        createEventButton = (Button) findViewById(R.id.create_activity_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createActivityIntent = new Intent(ClubHomeActivity.this, CreateEventActivity.class);
                createActivityIntent.putExtra("club", club);
                startActivity(createActivityIntent);
            }
        });
        clubName = (TextView) findViewById(R.id.club_name_tv);

        clubName.setText(club.getClubName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_club_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_logout:
                Intent signUpIntent = new Intent(ClubHomeActivity.this, MainActivity.class);
                signUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                signUpIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(signUpIntent);
                break;
            case R.id.action_edit_members:
                Intent editMembersIntent = new Intent(ClubHomeActivity.this, EditFriendsActivity.class);
                editMembersIntent.putExtra("club", club);
                startActivity(editMembersIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}