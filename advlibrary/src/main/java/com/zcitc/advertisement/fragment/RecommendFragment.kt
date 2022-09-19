package com.zcitc.advertisement.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.zcitc.advertisement.R
import com.zcitc.advertisement.adapter.RecommendAdapter
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_AGENCY_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_AGENT_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_CONSULTANT_ADS
import com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_FINANCIAL_INSTITUTION_ADS
import com.zcitc.advertisement.adv.AdvClickLisener
import com.zcitc.advertisement.adv.AdvStatisticsUtils
import com.zcitc.advertisement.adv.getAdvList
import com.zcitc.advertisement.bean.ADPlanItemsData
import com.zcitc.advertisement.databinding.FragmentHomeRecommendBinding
//import com.zcitc.advertisement.utils.AppManager
import com.zcitc.baselibrary.base.BaseDBFragment
import com.zcitc.baselibrary.base.BaseVModel
import com.zcitc.utilslibrary.utils.LiveDataBus

import java.util.*

/**
 *   author : LiuJie
 *   date   : 2021/3/114:33
 */
class RecommendFragment : BaseDBFragment<BaseVModel, FragmentHomeRecommendBinding>(FragmentHomeRecommendBinding::inflate)  {

    override fun getViewModel(): Class<BaseVModel> = BaseVModel::class.java

    companion object {
        fun newInstance(type: String): RecommendFragment {
            val args = Bundle()
            args.putString("type", type)
            val fragment = RecommendFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home_recommend, container, false)
    }


    var type: String = ""
    var dataList: MutableList<ADPlanItemsData> = ArrayList()
    var adapter: RecommendAdapter? = null
    @SuppressLint("UseRequireInsteadOfGet")
    override fun initData(savedInstanceState: Bundle?) {

        type = arguments!!.getString("type","")
        dataList.clear()
        dataList.addAll(getAdvList(activity!!,type))

        mBinding.recommendRecyclerView.layoutManager = GridLayoutManager(activity, 3)

        if (type.equals(FIRST_PAGE_CONSULTANT_ADS)) {
            //优秀顾问,
            adapter = RecommendAdapter(activity,dataList, R.layout.item_recommend, 11)

        } else if (type.equals(FIRST_PAGE_AGENT_ADS)) {
            //优秀中介
            adapter = RecommendAdapter(activity,dataList, R.layout.item_recommend, 21)
        } else if (type.equals(FIRST_PAGE_AGENCY_ADS)) {
            //优秀门店
            adapter = RecommendAdapter(activity,dataList, R.layout.item_recommend2, 1)
        } else if (type.equals(FIRST_PAGE_FINANCIAL_INSTITUTION_ADS)) {
            if(dataList.size>0){
                if (dataList[0].contentType.equals("1")) {
                    for (it in dataList) {
                        for (it2 in it.texts) {
                            if (it2.code == "title") {
                                it.name = it2.content
                            }
                            if (it2.code == "subTitle") {
                                it.content = it2.content
                            }
                        }


                    }
                }
            }

            //金融机构
            adapter = RecommendAdapter(activity,dataList, R.layout.item_recommend3, 31)
        }else{
            adapter = RecommendAdapter(activity,dataList, R.layout.item_recommend3, 31)
        }

        mBinding.recommendRecyclerView.adapter = adapter
        val emptyView: View = layoutInflater.inflate(R.layout.item_empty_layout3, null)
        adapter?.setEmptyView(emptyView)

        adapter?.setOnItemClickListener { _, _, position ->
            try {
                AdvStatisticsUtils().saveAdvStatisticsdData(activity!!,AdvStatisticsUtils().changeData(activity!!, dataList[position]))
                if (dataList[position] != null) {
                    val msg = Message()
                    msg.obj = dataList[position].eventLink
                    msg.what = 100

                    LiveDataBus.get().with("RECOMMEND_ADV_CLICK").postValue(msg)
//                    AppManager.getAppManager().startWeb(dataList[position].eventLink, "")
                }
            }catch (e: Exception) {}


        }
        if (!dataList.isNullOrEmpty()) {
            activity?.let {
                AdvStatisticsUtils().saveDisplayRecordData(activity!!,dataList,type+"DisplayRecord")

            }
        }



    }

    override fun setData(data: Any?) {

    }


}