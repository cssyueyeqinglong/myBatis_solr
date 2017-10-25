package com.cy.mybatis.contraler;

import com.cy.mybatis.poj.Items;
import com.cy.mybatis.service.ItemsService;
import com.cy.mybatis.vo.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/item")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/list")
    public ModelAndView list() {

        List<Items> list = itemsService.getList();
        ModelAndView mv = new ModelAndView();
        mv.addObject("itemList", list);
        mv.setViewName("itemList");//会从smvc配置文件中匹配前后缀完善路径
        return mv;
    }

    @RequestMapping("/itemEdit")
    public String itemEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        String id = request.getParameter("id");
        Items item = itemsService.findById(Integer.valueOf(id));
        model.addAttribute("item", item);
        return "editItem";
    }


    /**
     * @param pictureFile 上传的文件名
     * @param id
     * @param name
     * @param price
     * @param detail
     * @param createtime
     * @return
     */
    @RequestMapping("/updateitem")
    public String update(MultipartFile pictureFile, Integer id, String name, Float price, String detail, Date createtime) throws Exception {
        //获取图片的完整路径
        String fileStr = pictureFile.getOriginalFilename();
        String newfile = UUID.randomUUID().toString() + new Date().getTime() + fileStr.substring(fileStr.lastIndexOf("."));
        File file = new File("e:/img/" + newfile);
        if (!file.exists()) {
            file.mkdirs();
        }
        pictureFile.transferTo(file);
        Items items = new Items();
        items.setPic(file.getAbsolutePath());
        items.setId(id);
        items.setName(name);
        items.setPrice(price);
        items.setDetail(detail);
        items.setCreatetime(createtime);
        itemsService.update(items);
        return "forward:list.action";
    }

    @RequestMapping("/itemDeleteAll")
    public String deleteAll(ItemVo itemVo) {
        int[] ids = itemVo.ids;
        System.out.println("ids:=" + ids);
        itemsService.deleteAll(ids);
        return "forward:list.action";
    }

}
