package com.oil.detection.web.base;

import com.oil.detection.web.controller.DictionaryController;
import com.oil.detection.web.controller.DiscoveryCarController;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/test")
public class PageController extends BaseControllor {

    private static HashMap<String, HashMap<String, String[]>> classHashMap = null;
    private static HashMap<String, HashMap<String, String[]>> classMethodMapParams = null;
    private static ArrayList<Class<?>> service = null;

    public void init() {
        service = new ArrayList<Class<?>>();

        service.add(DictionaryController.class);
        service.add(DiscoveryCarController.class);
    }

    @RequestMapping(value = "/**", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=utf-8")
    public void test(HttpServletRequest request, HttpServletResponse response) {

        try {

            PrintWriter out = response.getWriter();

            response.setContentType("text/html;charset=utf-8");
            out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
            out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
            out.println("<title>Welcome</title>");
            out.println("<head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");

            out.println("<style type='text/css'>");
            out.println("body,div,ul,li,p,h1,h2{ margin:0; padding:0; border:0; background:#FAFAFA; font-family:Arial, Helvetica, sans-serif,'宋体'}");
            out.println("body{ text-align:center; font-size:12px}");
            out.println("li{ list-style:none}");
            out.println(".rolinList{ width:800px; height:auto; margin:20px auto 0 auto; text-align:left}");
            out.println(".rolinList li{margin-bottom:1px;border:1px solid #DADADA}");
            out.println(".rolinList li h2{ width:auto; height:40px;  background:#fff; font-size:14px; line-height:40px; padding-left:20px; color:#333; cursor:pointer}");
            out.println(".content{ height:380px; width:auto;  background:#fff;  background:#FAFAFA}");
            out.println(".content p{ margin:12px}");

            out.println("</style>");

            out.println("<script language='JavaScript' src='js/main.js'></script>");
            // out.println("<script language='JavaScript' src='" + requestURL + "common/main.js'></script>");
            out.println("</head><body>");

            out.println("<ul class='rolinList' id='rolin'>");

            HashMap<String, HashMap<String, String[]>> classInfo = getClassInfo();

            for (String service : classInfo.keySet()) {

                out.println("<li>");
                out.println("<h2>" + service + "</h2>");
                out.println("<div class='content'>");
                out.println("<p>接口列表：");

                HashMap<String, String[]> methods = classInfo.get(service);

                ArrayList<String> methodsList = new ArrayList<String>();

                for (String string : methods.keySet()) {
                    methodsList.add(string);
                }

                for (int j = 0; j < methodsList.size(); j++) {

                    if (j % 8 == 0) {
                        out.println("<br />");
                        out.println("<br />");
                    }

                    String methodName = methodsList.get(j);
                    String[] split = methodName.split("/");
                    String string = split[split.length - 1];

                    out.println("<label onclick=\"showDiv('" + service + "." + methodsList.get(j) + "')\">" + string + "</label>");
                    out.println("&nbsp;&nbsp;");
                }
                out.println("<hr style='borber:1px; color:gray; width: 95%;' />");
                for (int index = 0; index < methodsList.size(); index++) {

                    out.println("<form action='" + request.getContextPath() + methodsList.get(index) + "' method='get' target='_blank'>");
                    out.println("<div style='display: none;' id='" + service + "." + methodsList.get(index) + "'>");

                    out.println("<table><tr>");

                    String[] params = methods.get(methodsList.get(index));

                    if (params.length == 0) {
                        out.println(methodsList.get(index) + "为无参方法");
                        String defaultMD5 = "098F6BCD4621D373CADE4E832627B4F1";
                        out.println("<td><input type='hidden' name='sign' value='" + defaultMD5 + "' /></td>");
                        out.println("</tr><br /><br /><br /><tr><td colspan='2'><input type='submit' value='GO' style='width:50px;' /></td></tr></table></div>");

                    }

                    for (int j = 0; j < params.length; j++) {

                        int md5Flag = 0;
                        if (params[j].equalsIgnoreCase("sign")) {
                            md5Flag = 1;
                        } else {
                            out.println("<td width='80px'>" + params[j] + "</td>");
                            out.println("<td><input type='text' style='width: 250px;' name='" + params[j] + "' value='' /></td>");
                        }

                        if (j == 0) {
                        } else if ((j - md5Flag) == 1) {
                            out.println("</tr>");
                        } else if ((j + 1 - md5Flag) % 2 == 0) {
                            out.println("</tr><tr>");
                        }

                        if (j == params.length - 1) {
                            if ((j - md5Flag) % 2 == 0) {
                                out.println("<td></td>");
                            }
                            String defaultMD5 = "098F6BCD4621D373CADE4E832627B4F1";
                            out.println("<td><input type='hidden' name='sign' value='" + defaultMD5 + "' /></td>");
                            out.println("</tr><tr><td colspan='2'><input type='submit' value='GO' style='width:50px;' /></td></tr></table></div>");
                        }
                    }
                    out.println("<div id='res." + service + "." + methodsList.get(index) + "' />");
                    out.println("</div>");
                    out.println("</form>");
                }
                out.println("</div>");
                out.println("</li>");
            }
            out.println("</ul></body></html>");

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private HashMap<String, HashMap<String, String[]>> getClassInfo() {

        if (classHashMap == null || classHashMap.size() == 0) {
            classHashMap = new HashMap<String, HashMap<String, String[]>>();

            init();

            for (Class<?> clazz : service) {

                String classPath = "";
                RequestMapping cAnnotation = clazz.getAnnotation(RequestMapping.class);
                if (cAnnotation != null) {
                    String[] value = cAnnotation.value();
                    if (value.length > 0) {
                        classPath = value[0];
                    }
                }

                HashMap<String, String[]> methodMap = new HashMap<String, String[]>();

                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {

                    String methodPath = "";
                    RequestMapping mAnnotation = method.getAnnotation(RequestMapping.class);
                    if (mAnnotation != null) {
                        String[] value = mAnnotation.value();
                        if (value.length > 0) {
                            methodPath = classPath + value[0];
                        }
                    } else {
                        continue;
                    }
                    methodMap.put(methodPath, getClassMethodMapParams(service).get(clazz.getSimpleName()).get(method.getName()));
                }
                classHashMap.put(clazz.getSimpleName(), methodMap);
            }
        }
        return classHashMap;
    }

    public static HashMap<String, HashMap<String, String[]>> getClassMethodMapParams(List<Class<?>> ClassList) {

        if (classMethodMapParams == null) {
            classMethodMapParams = new HashMap<String, HashMap<String, String[]>>();
            for (Class<?> class1 : ClassList) {
                HashMap<String, String[]> methodMappingParams = methodMappingParams(class1);
                classMethodMapParams.put(class1.getSimpleName(), methodMappingParams);
            }
        }
        return classMethodMapParams;
    }

    public static HashMap<String, String[]> methodMappingParams(Class<?> forName) {

        String[] variableNames = null;

        HashMap<String, String[]> methodMappingParams = new HashMap<String, String[]>();
        try {

            ClassPool defClassPool = ClassPool.getDefault();

            defClassPool.insertClassPath(new ClassClassPath(forName));

            CtClass ctClass = defClassPool.get(forName.getName());

            CtMethod[] methods = ctClass.getDeclaredMethods();

            for (CtMethod method : methods) {
                MethodInfo methodInfo = method.getMethodInfo();
                CodeAttribute codeAttribute = methodInfo.getCodeAttribute();

                LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);

                CtClass[] parameterTypes = method.getParameterTypes();

                int[] local = new int[parameterTypes.length];
                int staticIndex = Modifier.isStatic(method.getModifiers()) ? 0 : 1;

                ArrayList<String> arrayList = new ArrayList<String>();

                for (int i = 0; i < local.length; i++) {
                    if (isBaseType(parameterTypes[i])) {
                        arrayList.add(attribute.variableName(i + staticIndex));
                    } else if (!isReqOrRes(parameterTypes[i])) {
                        CtClass ctClass2 = parameterTypes[i];
                        CtField[] declaredFields = ctClass2.getDeclaredFields();
                        for (CtField ctField : declaredFields) {
                            int modifiers = ctField.getModifiers();
                            if ((modifiers & java.lang.reflect.Modifier.STATIC) == 0) {
                                arrayList.add(ctField.getName());
                                // System.out.println(java.lang.reflect.Modifier.toString(modifiers));
                            }
                        }
                    }
                }

                variableNames = new String[arrayList.size()];
                arrayList.toArray(variableNames);

                methodMappingParams.put(method.getName(), variableNames);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return methodMappingParams;
    }

    public static boolean isBaseType(CtClass clazz) {

        String name = clazz.getName();

        if (name.equals(String.class.getName())) {
            return true;
        } else if (name.equals(Integer.class.getName())) {
            return true;
        } else if (name.equals(Long.class.getName())) {
            return true;
        } else if (name.equals(Float.class.getName())) {
            return true;
        } else if (name.equals(Double.class.getName())) {
            return true;
        } else if (name.equals(Boolean.class.getName())) {
            return true;
        }

        if (name.equals("int")) {
            return true;
        } else if (name.equals("long")) {
            return true;
        } else if (name.equals("float")) {
            return true;
        } else if (name.equals("double")) {
            return true;
        } else if (name.equals("boolean")) {
            return true;
        }

        return false;
    }

    public static boolean isReqOrRes(CtClass clazz) {

        String name = clazz.getName();

        if (name.equals(HttpServletResponse.class.getName())) {
            return true;
        } else if (name.equals(HttpServletRequest.class.getName())) {
            return true;
        }

        return false;
    }
}
