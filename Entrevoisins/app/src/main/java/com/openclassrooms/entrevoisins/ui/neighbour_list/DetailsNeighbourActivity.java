package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.EXTRA_NEIGHBOUR;

public class DetailsNeighbourActivity extends AppCompatActivity {


    private NeighbourApiService mApiService = DI.getNeighbourApiService();


    @BindView(R.id.detail_screen_name)
    TextView mDetailScreenName;
    @BindView(R.id.detail_screen_photo)
    ImageView mDetailScreenPhoto;
    @BindView(R.id.detail_screen_fav)
    FloatingActionButton mDetailScreenFav;
    @BindView(R.id.detail_screen_name_2)
    TextView mDetailScreenName2;
    @BindView(R.id.detail_screen_fb_text)
    TextView mDetailScreenFbText;
    @BindView(R.id.detail_screen_back)
    ImageButton mDetailScreenBack;
    @BindView(R.id.detail_screen_desc)
    TextView mDetailScreenDesc;
    Neighbour neighbour = new Neighbour();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_screen);
        ButterKnife.bind(this);
        neighbour = (Neighbour) getIntent().getExtras().get(EXTRA_NEIGHBOUR);
        mDetailScreenDesc.setText(neighbour.getAboutMe());
        mDetailScreenName.setText(neighbour.getName());
        mDetailScreenName2.setText(neighbour.getName());
        mDetailScreenFbText.setText("www.facebook.fr/" + neighbour.getName());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mDetailScreenPhoto);
        favBtn(neighbour.getFavorite());
        mDetailScreenFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upFav(neighbour.getFavorite());
            }
        });
        mDetailScreenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCloseActivity(neighbour);
            }
        });
    }

    private void upFav (boolean isFavorite) {
        favBtn(!isFavorite);
        neighbour.setFavorite(!isFavorite);
    }

    private void favBtn(boolean isFavorite) {
        if (isFavorite) {
            mDetailScreenFav.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            mDetailScreenFav.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

    private void onCloseActivity(Neighbour neighbour){
        mApiService.updateFavorites(neighbour);
        finish();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        onCloseActivity(neighbour);
    }
}

