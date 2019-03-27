package com.serendipity.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.serendipity.common.pojo.EasyUITreeNode;
import com.serendipity.mapper.TbItemCatMapper;
import com.serendipity.pojo.TbItemCat;
import com.serendipity.pojo.TbItemCatExample;
import com.serendipity.pojo.TbItemCatExample.Criteria;
import com.serendipity.service.ItemCatService;

/**
 * 商品分类管理
 * 
 * @author gqh
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemMapper;

    @Override
    public List<EasyUITreeNode> getItemCatlist(long parentId) {
        // TODO Auto-generated method stub
        // 根据parentId查询子节点列表
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        // 设置查询条件
        criteria.andParentIdEqualTo(parentId);
        // 执行查询
        List<TbItemCat> list = itemMapper.selectByExample(example);
        // 创建返回结果list
        List<EasyUITreeNode> resultList = new ArrayList<>();
        // 把列表转成EasyUITreeNod列表
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            // 设置属性
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "open");
            // 添加到结果列表
            resultList.add(node);
        }
        // 返回结果
        return resultList;
    }

}
