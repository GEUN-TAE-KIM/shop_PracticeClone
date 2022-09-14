package org.cream.udemshoppingproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import org.cream.udemshoppingproject.assets.AssetLoder
import org.json.JSONObject

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        val assetLoader = AssetLoder()
        //requireContext를 하는 건 context가 널러블이기 떄문
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home,json")
        Log.d("homeData", homeJsonString ?: "")

        //json 데이터 파싱
        if (!homeJsonString.isNullOrEmpty()) {
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomData::class.java)

            toolbarTitle.text =  homeData.title.text
            GlideApp.with(this)
                .load(homeData.title.iconUrl)
                .into(toolbarIcon)


            /*
            val title = jsonObject.getJSONObject("title")
            val text = title.getString("text")
            val iconUrl = title.getString("icon_url")
            val titleValue = Title(text, iconUrl)

            // 길슨 라이브러리를 사용하기 전이면 json 오브젝트들을 일일이 쳐서 가져와야함
            val topBanners = jsonObject.getJSONArray("top_banners")
            val size = topBanners.length()
            for (index in 0 until size) {
                val bannerObject = topBanners.getJSONObject(index)
                val backgroundImageUrl = bannerObject.getString("background_image_url")
                val badgeObject = bannerObject.getJSONObject("badge")
                val badgeLabel = badgeObject.getString("Label")
                val badgeBackGroundColor = badgeObject.getString("background_color")
                val bannerBadge = BannerBadage(badgeLabel, badgeBackGroundColor)

                val banner = Banner(
                    backgroundImageUrl,
                    bannerBadge,
                    bannerLabel,
                    bannerProductDetail
                )*/

            viewpager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)

            }
        }
    }
}