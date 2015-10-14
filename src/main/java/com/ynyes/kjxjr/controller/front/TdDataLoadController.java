package com.ynyes.kjxjr.controller.front;

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

import com.ynyes.kjxjr.entity.TdNavigationMenu;
import com.ynyes.kjxjr.service.TdArticleService;
import com.ynyes.kjxjr.service.TdCommonService;
import com.ynyes.kjxjr.service.TdNavigationMenuService;
import com.ynyes.kjxjr.util.SiteMagConstant;

@Controller
public class TdDataLoadController {
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
	private TdArticleService tdArticleService;
	
	@Autowired
	private TdNavigationMenuService tdNavigationMenuService;
	
	String filepath = SiteMagConstant.imagePath;

	
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
        
        File file = new File(filepath +"/" + name);
        
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
        else 
        {
        	return;
        }
    }
}
