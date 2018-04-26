package com.zjitc.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/10/30 15:48
 * @description: 截获访问tomcat中的所有请求，然后决定给哪个control中的方法进行调用
 */
@WebServlet(name = "dispatch", urlPatterns = {"/"}, loadOnStartup = 3)
public class DispatchServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(DispatchServlet.class);
  private static final String METHOD_POST = "POST";
  /**
   * 每一个url对应一个method
   */
  private Map<String, Method> methodMap;
  /**
   * 每个方法名对应一个类描述
   */
  private Map<Method, Object> classMap;

  @Override
  public void init() throws ServletException {
    methodMap = new HashMap<>(10);
    classMap = new HashMap<>(10);
    try {
      List<Class> clz = getAllClassFromPackage("com.zjitc.control");

      if (clz == null || clz.size() == 0) {
        log.warn("there is no controls in your server for receive request!");
        return;
      }

      for (Class c : clz) {
        log.info(c.getName());
        Method[] methods = c.getMethods();
        Object instance = c.newInstance();
        for (Method m : methods) {
          RequestMapping annotation = m.getAnnotation(RequestMapping.class);

          if (annotation != null) {
            classMap.put(m, instance);
            String url = this.getServletConfig().getServletContext().getContextPath() + annotation.url();
            log.info(url);
            methodMap.put(url, m);
          }
        }
      }
    } catch (Exception e) {
      log.error(e.getCause().getMessage());
      throw new ExceptionInInitializerError(e);
    }
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String url = req.getRequestURI();
    log.info("request url = " + url);
    Method method = methodMap.get(url);

    if (method == null) {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND, "该页面不存在");
      return;
    }

    try {
      if (METHOD_POST.equals(req.getMethod())) {
        req.setCharacterEncoding("utf-8");
      }
      method.invoke(classMap.get(method), new EncodeHttpServletRequest(req), resp);
    } catch (Exception ex) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getCause().getMessage());
      return;
    }
  }

  /**
   * 找寻某个包路径下的所有的类描述文件
   *
   * @param packName 包路径(com.zjitc.control)
   * @return 返回所有的类描述文件
   */
  private static List<Class> getAllClassFromPackage(String packName) throws ClassNotFoundException {
    List<Class> clas = new ArrayList<Class>();
    String path = packName.replace('.', '/');

    String classPath = DispatchServlet.class.getResource("/").getPath().replace("%20", " ") + path;

    log.info("classpath: " + classPath);
    try {
      // 该路径下的所有文件的名字
      File fileRoot = new File(classPath);
      File[] files = fileRoot.listFiles();
      if (files == null) {
        log.info(fileRoot);
        log.info("files is null");
        return null;
      }

      for (File file : files) {
        String name = file.getName();
        log.info(name);
        String cname = name.substring(0, name.length() - 6);
        String fullClass = packName + "." + cname;
        clas.add(Class.forName(fullClass));
      }
    }
    catch (Exception e) {
      log.error(e.getCause().getMessage());
      throw e;
    }
    return clas;
  }
}
