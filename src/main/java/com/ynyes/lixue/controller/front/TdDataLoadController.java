package com.ynyes.lixue.controller.front;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.lixue.service.TdArticleService;
import com.ynyes.lixue.service.TdCommonService;
import com.ynyes.lixue.util.SiteMagConstant;

@Controller
public class TdDataLoadController {
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
	private TdArticleService tdArticleService;
	
	String filepath = "src\\main\\resources\\static\\";
	@RequestMapping("/download")
	public String  down(HttpServletRequest req,ModelMap map,Integer page)
	{
		tdCommonService.setHeader(map, req);
		if(page == null)
		{
			map.addAttribute("load_data_page",tdArticleService.findByMenuId(83L, 0, 10));
		}
		else
		{
			map.addAttribute("load_data_page",tdArticleService.findByMenuId(83L, page, 10));
		}
		
		return "/client/download";
	}
	
	@RequestMapping(value="/download/data", method = RequestMethod.GET)
    @ResponseBody
    public void download(String name,
                HttpServletResponse resp,
                HttpServletRequest req) throws IOException {
        if (null == name)
        {
            return;
        }
        
        OutputStream os = resp.getOutputStream();  
        
        File file = new File(filepath + name);
        
        if (file.exists())
        {
            try {
                resp.reset();
                resp.setHeader("Content-Disposition", "attachment; filename="
                        + name);
                resp.setContentType("application/octet-stream; charset=utf-8");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
    }
}
