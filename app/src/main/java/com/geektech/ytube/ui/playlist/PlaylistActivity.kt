package com.geektech.ytube.ui.playlist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.geektech.ytube.base.BaseActivity
import com.geektech.ytube.core.utils.CheckInternetConnection
import com.geektech.ytube.core.utils.Constant
import com.geektech.ytube.data.remote.model.Item
import com.geektech.ytube.databinding.ActivityPlaylistsBinding
import com.geektech.ytube.ui.detail.DetailPlaylistActivity

class PlaylistActivity : BaseActivity<ActivityPlaylistsBinding>(){

   private lateinit var viewModel: PlaylistViewModel
       private  var adapter:PlaylistsAdapter? = null

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initAdapter() {
          adapter = PlaylistsAdapter()
adapter?.onClick = {
    val intent = Intent(this, DetailPlaylistActivity::class.java)
    intent.putExtra(Constant.putId, it.id)
    startActivity(intent)}
        binding.recyclerview.adapter=adapter
    }

     fun initListener(id: Item) {


    }
    override fun setUI() {
        viewModel=ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun setupLiveData() {
        viewModel.getPlaylist().observe(this){
            Toast.makeText(this,it.kind.toString(),Toast.LENGTH_SHORT).show()
            adapter?.setList(it.items as ArrayList<Item>)

        }
    }

    override fun checkInternet() {
        CheckInternetConnection((getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager)).observe(this) {

            binding.includedInternet.rlParent.isVisible = !it
            binding.recyclerview.isVisible=it
            setupLiveData()
        }
    }
}


