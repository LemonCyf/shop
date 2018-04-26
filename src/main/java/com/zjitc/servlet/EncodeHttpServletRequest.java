package com.zjitc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/11/7 9:21
 * @description: ${description}
 */
public class EncodeHttpServletRequest extends HttpServletRequestWrapper {

  private Map<String, String[]> values;
  /**
   * Constructs a request object wrapping the given request.
   *
   * @throws IllegalArgumentException if the request is null
   */
  public EncodeHttpServletRequest(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String getParameter(String name) {
    String value = super.getParameter(name);
    return getUtf8(value);
  }

  @Override
  public Map<String, String[]> getParameterMap() {
    if (values == null) {
      Map<String, String[]> params = super.getParameterMap();
      values = new HashMap<String, String[]>(params.size());

      Set<Entry<String, String[]>> entries = params.entrySet();
      for (Entry<String, String[]> item : entries) {
        String[] vs = item.getValue();
        String[] encode = new String[item.getValue().length];

        for (int i = 0; i < vs.length; i++) {
          encode[i] = getUtf8(vs[i]);
        }
        values.put(item.getKey(), encode);
      }
    }

    return values;
  }

  /**
   * 将字符old以utf-8的方式进行编码
   *
   * @param old
   * @return
   */
  private String getUtf8(String old)  {
    try {
      return old;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
