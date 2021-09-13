package com.example.streamit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.streamit.adapter.BannerMoviesPageAdapter;
import com.example.streamit.adapter.MainRecyclerAdapter;
import com.example.streamit.model.AllCategory;
import com.example.streamit.model.BannerMovies;
import com.example.streamit.model.CategoryItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    BannerMoviesPageAdapter bannerMoviesPageAdapter;
    TabLayout indicatorTab, categoryTab;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;
    Timer sliderTimer;
    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tabIndicator);
        categoryTab = findViewById(R.id.tabLayout);
        nestedScrollView = findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "The Family Man 2", "https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_TheFamilyManS2Review/cc4c0601-14a3-444e-86c7-362fc3ed42dc._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/d0a2/eedc/98c2/47b7-80cb-87269852ac29/e3c5c278-69db-4991-a205-fb5344ef5be5_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=dm3xAGHkXMzBd7TpJuAFvdrGxN3rZIO4mp8PnxU0UfhQqIWJd0CLQ7JISsrExAjfsLHBXGhgQNU1EfPm4VGoTUp0HlGOU2bLzqpC8~nxVHz1FQT~PeIiMsVpUvUjqQJ30tWJ6AGKLrpJBOMZA8ZhtRJnNkcZf9Or2U1ImimDbug6rQWzKf2S1w0nMBDjCp1IRZS4RJ1dY0wsPGqoMG7-L5wmlGO2tNDJt0k9xSd-THtxewgTcHYhvYD4abFXQJJLL9lg6E7PbcCqs6ptYyQYxbhr15SF2YirXTLLPUTIn36Z5Ie4YfdHF2uHI5~6SK0irAWaZOAIAcEXKZurrGG6kg__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeBannerList.add(new BannerMovies(2, "Dom", "https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_DOMLaunch/e7f8291e-01fe-46b0-98e1-016bd3f378fb._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeBannerList.add(new BannerMovies(3, "World's Toughest Race", "https://m.media-amazon.com/images/G/01/digital/video/sonata/PV_IN_WorldstoughestRace/a98ab7b1-5c02-4d60-ae25-89969aea08aa._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeBannerList.add(new BannerMovies(4, "Ha Ha kar", "https://m.media-amazon.com/images/G/01/digital/video/sonata/Hero_IN_hahakar/1f347eb2-8e1a-4ac5-9a19-92e45e6a1188._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeBannerList.add(new BannerMovies(5, "War", "https://m.media-amazon.com/images/G/01/digital/video/sonata/PV_IN_War/d867a0c0-a7c2-4ae2-b93f-636e0c07e259._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1, "The Family Man 2", "https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_TheFamilyManS2Review/cc4c0601-14a3-444e-86c7-362fc3ed42dc._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        tvShowBannerList.add(new BannerMovies(2, "Dom", "https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_DOMLaunch/e7f8291e-01fe-46b0-98e1-016bd3f378fb._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        tvShowBannerList.add(new BannerMovies(3, "World's Toughest Race", "https://m.media-amazon.com/images/G/01/digital/video/sonata/PV_IN_WorldstoughestRace/a98ab7b1-5c02-4d60-ae25-89969aea08aa._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        tvShowBannerList.add(new BannerMovies(4, "Time Enna Boss?", "https://m.media-amazon.com/images/G/01/digital/video/sonata/PV_IN_TimeEnnaBossLaunch/ae3f36b6-f70b-4ebc-877e-7cc678b8f60f._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        tvShowBannerList.add(new BannerMovies(5, "bahubali", "https://m.media-amazon.com/images/G/01/digital/video/sonata/Hero_PV_IN_Baahubali_Kids_S4/db831745-e023-4149-ae1f-6828838fb206._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1, "Pink", "https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_PinkDocumentaryLaunch/9b5a2778-0c90-4f43-9f0f-8a96f5e57e6f._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        movieBannerList.add(new BannerMovies(2, "Gulabo Sitabo", "https://m.media-amazon.com/images/G/01/digital/video/sonata/PV_IN_GulaboSitaboReviews/0df78c8d-277b-4ea5-aa58-e4a6e8dd603e._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        movieBannerList.add(new BannerMovies(3, "Kong", "https://m.media-amazon.com/images/G/01/digital/video/sonata/Hero_IN_Kongskull/d9d897aa-01f9-41f4-8b19-1f307cf4533c._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        movieBannerList.add(new BannerMovies(4, "Bank Chor", "https://m.media-amazon.com/images/G/01/digital/video/sonata/Hero_IN_Bankchor_v1/8bc6bb53-0c15-4cdc-a5e8-11325b90b02f._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        movieBannerList.add(new BannerMovies(5, "Door ke Darshan", "https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_DoorKeDarshan/a7f9f27c-225b-4472-984d-0bb3538e2f2e._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "Shiva", "https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_ShivaS1Launch/bf73d6ec-35d5-4017-859e-5201d6f7bd64._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        kidsBannerList.add(new BannerMovies(2, "Kung Fu Dhamaka", "https://m.media-amazon.com/images/G/01/digital/video/sonata/Hero_PV_IN_Chota_Bheem_v2/7afa4b06-375a-4a18-bf02-b942a41df4fa._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        kidsBannerList.add(new BannerMovies(3, "Whishenpoof", "https://m.media-amazon.com/images/G/01/digital/video/sonata/gl2_wishenpoof_s2c/4dfc3cc3-16c4-4279-9ca7-4df51c5bf040._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        kidsBannerList.add(new BannerMovies(4, "Peppa Pig", "https://m.media-amazon.com/images/G/01/digital/video/sonata/Hero_IN_Peppa/96c9dd4a-365b-4191-ad9d-b4f84c791344._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        kidsBannerList.add(new BannerMovies(5, "Bug Diaries", "https://m.media-amazon.com/images/G/01/digital/video/sonata/PV_IN_BugDiariesHalloween/485bade3-e38d-4aae-8a6a-2018975a16ba._UR3000,600_SX1500_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));

        setBannerMoviesPageAdapter(homeBannerList);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 1:
                        setScrollDefaultState();
                        setBannerMoviesPageAdapter(tvShowBannerList);
                        return;
                    case 2:
                        setScrollDefaultState();
                        setBannerMoviesPageAdapter(movieBannerList);
                        return;
                    case 3:
                        setScrollDefaultState();
                        setBannerMoviesPageAdapter(kidsBannerList);
                        return;
                    default:
                        setScrollDefaultState();
                        setBannerMoviesPageAdapter(homeBannerList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override     
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // first we will call catItem data
        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"paatal lok", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/8d1872103a292a9ee0fdd9837e7a0e01934ddd298b8a7968426d653e90c72540._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem1.add(new CategoryItem(2,"The Family Man", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/dc0be6ae04e6eb4ea5fd564250445f38c0fe6243fc1a9d20ed9e93575ecaf561._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem1.add(new CategoryItem(3,"Panchayat", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/2407ebef02f8ce9f1218ecd1100725b2ce84718201b925e000ed1e561220cc1e._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem1.add(new CategoryItem(4,"Pushpavalli", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/719243ded6ddf6d09143425a1983ee4c10997198ea4c14985ba497e757bac9d7._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem1.add(new CategoryItem(5,"Breathe", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/870e7136c940bf80a82e74f5b6e885a7e95517b7bc2ce14de43d632ae2b24c5a._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));

        List<CategoryItem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem2.add(new CategoryItem(1,"The Family Man","https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_TheFamilyManS2Review/cc4c0601-14a3-444e-86c7-362fc3ed42dc._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem2.add(new CategoryItem(2,"Dom","https://m.media-amazon.com/images/S/sonata-images-prod/PV_IN_DOMReview/e50d04b6-caaf-4968-b22e-9f102b61605f._UR3000,600_SX1500_FMwebp_.jpeg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem2.add(new CategoryItem(3,"Comicstaan","https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/c1415166064a4fefd159e8706f7cff4d2daebf6d7b9297b91909652d9e67c5b5._UR1920,1080_RI_SX356_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem2.add(new CategoryItem(4,"Mirzapur","https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/a0e10852e4545f8bf79f17acccec3de03391210b9803bf82811a4fd0bd31f6d8._UR1920,1080_RI_SX356_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem2.add(new CategoryItem(5,"Dimapur","https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/870e7136c940bf80a82e74f5b6e885a7e95517b7bc2ce14de43d632ae2b24c5a._UR1920,1080_RI_SX356_FMwebp_.jpg","https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));

        List<CategoryItem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem3.add(new CategoryItem(1,"The Witches", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/82c58c54170ef5e1a9bcc8aa018aa827f715bc061f5da2a53f3c4391f90c7114._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem3.add(new CategoryItem(2,"Aarkkariyam", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/909e4f03fa76c43b5da84bd0442a0874cde5b8aa3ff077fd05ad2210d834da95._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem3.add(new CategoryItem(3,"Mohan Kumar Fans", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/ddeff395f6d2f9810cc063707e875e23c79c72d1187afb35da6c9e90ab543964._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem3.add(new CategoryItem(4,"Kala", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/1a8d98e7015229cf631afcd4ecc92c065060b3b7e9e7d94e927325a54ae80a45._UR1920,1080_RI_SX356_FMwebp_.jpg", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));
        homeCatListItem3.add(new CategoryItem(5,"The Power", "https://images-eu.ssl-images-amazon.com/images/S/pv-target-images/2d1aa1886e331f615cf089df9c7da30e1dbc5cb169df303d036e8c9cd7570360._UR1920,1080_RI_SX356_FMwebp_.png", "https://s3-iad-2.cf.trailer.row.aiv-cdn.net/9614/a01e/4efb/4ad4-a9dd-13e3d6784195/aa69459b-78ea-4cfd-970a-c0924d0ab1a2_video_900_audio_aaclc_128.mp4?Expires=1624311095&Signature=NY~lfpwE46dvbKIRpSPomfjr9UY~BSVLK3oWE2S7Vuf8CzaZiR9655WeovwfdV2L99KNtWYAUM9tX2RQ8ZuxALq04veRPuuPed4tthiIjWrxO32o5ot5i9wAjjINr4az-dIvXaXYegFpdOn5WDYrZBRubY0CD4Bec0WOJ903TWcm73W~t90OSJBnm7Th2xc9qYk62K813h1mosjgDjKKKLFFqFJcH8ve-AGfWlwbht9ZRqe1LvK9H5ik1UZeBkpT~LooKqstl58CvTKEIbuzPvmMiKGRzcZkkPjF8MAuKnXdYScBnmukIAuvesdYCzxpAjIZAeYfIap1b1zjowEbQA__&Key-Pair-Id=APKAJIYEUF5P2E3CCYTA"));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1,"Filmfare OTT Award Winners", homeCatListItem1));
        allCategoryList.add(new AllCategory(2,"Amazon Original series", homeCatListItem2));
        allCategoryList.add(new AllCategory(3,"Latest movies", homeCatListItem3));
        setMainRecycler(allCategoryList);
    }

    private void setBannerMoviesPageAdapter(List<BannerMovies> bannerMoviesList){
        bannerMoviesViewPager = findViewById(R.id.bannerViewPager);
        bannerMoviesPageAdapter = new BannerMoviesPageAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPageAdapter);

        indicatorTab.setupWithViewPager(bannerMoviesViewPager);

        sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);

    }

    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(bannerMoviesViewPager.getCurrentItem() < homeBannerList.size()-1){
                        bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem()+1);
                    }
                    else{
                        bannerMoviesViewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }
    public void setMainRecycler(List<AllCategory> allCategoryList){
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);

    }
    private void setScrollDefaultState(){
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0,0);
        appBarLayout.setExpanded(true);
    }


}