package servlet;


import entity.Users;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ServiceFactory;
import service.user.UserService;
import uite.PageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * main页面需要ajax使用的servlet
 */
@WebServlet("/home")
public class HomeServlet extends BaseServlet {

    private UserService userService;
    private Users users = new Users();

    @Override
    public Class getServletClass() {
        return HomeServlet.class;
    }

    //当用户访问我们这个servlet的时候 先执行init
    @Override
    public void init() throws ServletException {
        userService = (UserService) ServiceFactory.getServiceImpl("userService");
    }


    /**
     * 分页的方法
     */
    public PageUtil findAllByPage(HttpServletRequest req, HttpServletResponse resp) {
        //获取当前页面 pageIndex
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));

        //创建PageUtil对象
        PageUtil pageUtil = new PageUtil();
        //把获取的数据封装到PageUtil
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotalCount(userService.findRownum());
        //调用service代码 获取数据
        List<Users> list = userService.findAllByPage(pageUtil);
        pageUtil.setList(list);
        System.out.println("pageUtil===>" + pageUtil);
        //返回PageUtil
        return pageUtil;
    }


    public String deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        int num = userService.deleteByCondition(id);
        if (num > 0) {
            System.out.println("删除成功");
            return "main";
        }
        System.out.println("删除失败");
        return "login";
    }

    /**
     * 文件上传
     */
    public String upload(HttpServletRequest req, HttpServletResponse resp) {
//    //创建DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory();
//创建servletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
//首先判断是否为文件上传请求
        boolean flag = upload.isMultipartContent(req);
        if (flag) {
            try {
                List<FileItem> items = upload.parseRequest(req);
                Iterator<FileItem> iterator = items.iterator();
                while (iterator.hasNext()) {
//    获取每一个元素 普通元素 文件元素
                    FileItem item = iterator.next();
                    if (item.isFormField()) {
                        String fileName = item.getFieldName();
                        if (fileName.equals("userName")) {
                            users.setUserName(item.getString("utf-8"));
                        }
                    } else { //文件元素
                        String uploadPath = req.getSession().getServletContext().getRealPath("/upload");
                        File file = new File(uploadPath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        //获取上传文件
                        String name = item.getName();
                        if (name != null && !name.equals("")) { //看用户是否选择了文件
                            File uploadFile = new File(name);
                            File saveFile = new File(uploadPath, uploadFile.getName());
                            //真正的上传
                            item.write(saveFile);
                            users.setFile(uploadPath + "\\" + uploadFile.getName());
                        }
                    }
                }
                users.setEmail("我是郵箱");
                users.setUserType(0);
                users.setPassword("我是密碼");
                int num = userService.add(users);
                if (num > 0) {
                    System.out.println("文件上传成功！");
                } else {
                    System.out.println("文件上传失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("压根就不是文件上传请求！ enctype!!!");
        }
        return "login";
    }
}
