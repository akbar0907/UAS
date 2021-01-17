package com.github.UAS0678.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.UAS0678.R
import com.github.UAS0678.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        supportActionBar?.apply {
            elevation = 0f
            title = ""
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        username?.let { viewModel.setUserDetail(it) }

        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    detailLogin.text = it.login
                    detailURL.text = it.html_url
                    detailFollower.text = "${it.followers} ${getString(R.string.tab1)}"
                    detailFollowing.text =  "${it.following} ${getString(R.string.tab2)}"
                    detailRepos.text = "${it.public_repos} Repos"
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(detailAvatar)
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            detailPager.adapter = sectionPagerAdapter
            detailTab.setupWithViewPager(detailPager)
        }

    }


}
