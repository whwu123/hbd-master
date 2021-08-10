package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.common.constant.GlobalConstant;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.DateUtils;
import com.active4j.hr.core.util.FileUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.util.StringUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.func.upload.entity.UploadAttachmentEntity;
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.yc.entity.YcUploadAttachmentEntity;
import com.active4j.hr.yc.service.YcUploadAttachmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/ycFileController")
public class YcFileController extends BaseController {

    @Autowired
    private YcUploadAttachmentService ycUploadAttachmentService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "yc/file/filelist";
    }

    /**
     * 查询数据
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping("/datagrid")
    public void datagrid(YcUploadAttachmentEntity ycUploadAttachmentEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //拼接查询条件
        QueryWrapper<YcUploadAttachmentEntity> queryWrapper = QueryUtils.installQueryWrapper(ycUploadAttachmentEntity, request.getParameterMap(), dataGrid);
        //执行查询
        IPage<YcUploadAttachmentEntity> lstResult = ycUploadAttachmentService.page(new Page<YcUploadAttachmentEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        //结果处理，直接写到客户端
        ResponseUtil.writeJson(response, dataGrid, lstResult);
    }

    @RequestMapping(value = "/addfile", method = RequestMethod.GET)
    public String addfile(Model model) {
        return "yc/file/fileadd";
    }


    /**
     *
     * @description
     *  	文件上传
     * @return AjaxJson
     * @author guyp
     * @time 2020年4月16日 上午9:44:14
     */
    @RequestMapping("uploadFiles")
    @ResponseBody
    public AjaxJson uploadFiles(String db, MultipartHttpServletRequest request, HttpServletResponse response){
        AjaxJson j = new AjaxJson();
        try{
            Map<String, MultipartFile> fileMap = request.getFileMap();

            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

                MultipartFile mf = entity.getValue();// 获取上传文件对象
                String fileName = mf.getOriginalFilename();// 获取文件名
                String extend = FileUtils.getExtend(fileName);// 获取文件扩展名

                if(StringUtils.equals(db, GlobalConstant.FILE_UPLOADER_SAVE_FILE)) {
                    //保存文件系统

                    String strYYYYMMDD = DateUtils.getYYYYMMDDStr();

                    String realPath = request.getSession().getServletContext().getRealPath("/") + "/upload/" + strYYYYMMDD + "/";// 文件的硬盘真实路径
                    String path = "upload/" + strYYYYMMDD + "/";
                    File file = new File(realPath);
                    if (!file.exists()) {
                        file.mkdirs();// 创建根目录
                    }

                    String noextfilename = DateUtils.getDataString(DateUtils.SDF_YYYYMMDDHHMMSS) + StringUtil.random(8);//自定义文件名称
                    String myfilename= noextfilename+"."+extend;//自定义文件名称

                    String savePath = realPath + myfilename;// 文件保存全路径
                    path = path + myfilename;
                    File savefile = new File(savePath);

                    // 文件拷贝到指定硬盘目录
                    FileCopyUtils.copy(mf.getBytes(), savefile);

                    //保存进文件表
                    YcUploadAttachmentEntity attach = new YcUploadAttachmentEntity();
                    attach.setExtendName(extend);
                    attach.setName(fileName);
                    attach.setSaveName(myfilename);
                    attach.setPath(savePath);
                    attach.setUploaderDate(DateUtils.getDate());
                    attach.setUploaderName(ShiroUtils.getSessionUserName());
                    attach.setType(GlobalConstant.FILE_UPLOADER_TYPE_OTHER);
                    //保存
                    ycUploadAttachmentService.save(attach);

                    //页面返回值
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("filePath", attach.getId());
                    j.setAttributes(map);

                }else if(StringUtils.equals(db, GlobalConstant.FILE_UPLOADER_SAVE_DB)) {
                    //保存进数据库
                    YcUploadAttachmentEntity attach = new YcUploadAttachmentEntity();
                    attach.setContent(mf.getBytes());
                    attach.setName(fileName);
                    attach.setExtendName(extend);

                    String noextfilename = DateUtils.getDataString(DateUtils.SDF_YYYYMMDDHHMMSS) + StringUtil.random(10);//自定义文件名称
                    String myfilename= noextfilename+"."+extend;//自定义文件名称

                    attach.setSaveName(myfilename);
                    attach.setPath(GlobalConstant.CONFIG_FILE_SAVE_DB_URL + myfilename);
                    attach.setUploaderDate(DateUtils.getDate());
                    attach.setUploaderName(ShiroUtils.getSessionUserName());
                    attach.setType(GlobalConstant.FILE_UPLOADER_TYPE_OTHER);
                    ycUploadAttachmentService.save(attach);

                    //页面返回值
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("filePath", attach.getId());
                    j.setAttributes(map);
                }

                break;
            }

        }catch(Exception e){
            j.setSuccess(false);
            j.setMsg("文件上传失败");
            log.error("文件上传报错，错误信息：｛｝", e.getMessage());
        }

        return j;
    }

    /**
     *
     * @description
     *  	下载文件
     * @return void
     * @author guyp
     * @time 2020年4月15日 下午5:22:50
     */
    @RequestMapping("download")
    public void download(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            YcUploadAttachmentEntity tsAttachment = ycUploadAttachmentService.getById(id);

            if(null == tsAttachment) {
                return;
            }
            //本地文件下载
            if(StringUtils.equals(GlobalConstant.FILE_UPLOADER_SAVE_FILE, tsAttachment.getType())) {

                //获取文件路径
                String downLoadPath = tsAttachment.getPath();
                String fileName = tsAttachment.getName();
                //获取文件长度
                long fileLength = new File(downLoadPath).length();
                //设置文件输出类型
                response.setContentType("application/octet-stream");

                response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));

                //获取输入流
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath));
                //输出流
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                //关闭流
                bis.close();
                bos.close();
            }
            //数据库文件下载
            else if(StringUtils.equals(GlobalConstant.FILE_UPLOADER_SAVE_DB, tsAttachment.getType())){
                byte[] data = tsAttachment.getContent();
                String fileName = tsAttachment.getName();
                response.reset();
                response.setHeader("Content-Disposition", "attachment; filename=\"" +new String(fileName.getBytes(), "iso8859-1") + "\"");
                response.addHeader("Content-Length", "" + data.length);
                response.setContentType("application/octet-stream;charset=UTF-8");
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                outputStream.write(data);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            log.error("文件下载报错，错误原因：{}", e.getMessage());
            e.printStackTrace();
        }
    }


    @RequestMapping("/del")
    @ResponseBody
    @Log(type = LogType.del, name = "删除附件信息", memo = "删除附件信息")
    public AjaxJson del(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        try {

            if(StringUtils.isEmpty(id)) {
                j.setSuccess(false);
                j.setMsg("请选择需要删除的附件信息");
                return j;
            }
            ActiveUser user = ShiroUtils.getSessionUser();
            ycUploadAttachmentService.removeById(id);
            log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的附件信息");
        }catch(Exception e) {
            log.error("删除附件信息报错，错误信息：{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("删除附件信息错误");
            e.printStackTrace();
        }

        return j;
    }
}
