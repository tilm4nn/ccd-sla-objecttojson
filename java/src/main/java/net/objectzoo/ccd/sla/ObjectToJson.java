/*
 * The MIT License
 *
 * Copyright (C) 2011 Tilmann Kuhn
 *
 * http://www.object-zoo.net
 *
 * mailto:ccd-training@object-zoo.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.objectzoo.ccd.sla;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by tilmann on 06.06.2016.
 */
public class ObjectToJson
{

    public String objectToJson(Object object)
    {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        Method[] methods = object.getClass().getMethods();
        String propertySeparator = "";
        for (Method method : methods)
        {
            String methodName = method.getName();
            if (methodName.startsWith("get") && method.getParameterCount() == 0 && !methodName.equals("getClass"))
            {
                String propertyName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                Class<?> propertyType = method.getReturnType();
                Object propertyValue;
                try
                {
                    propertyValue = method.invoke(object);
                }
                catch (IllegalAccessException | InvocationTargetException e)
                {
                    throw new IllegalArgumentException(
                        "Could not access property " + propertyName, e);
                }

                String propertyValueJson;
                if (propertyValue == null)
                {
                    propertyValueJson = "null";
                }
                else if (propertyValue instanceof String)
                {
                    propertyValueJson = "\"" + propertyValue + "\"";
                }
                else if (propertyValue instanceof Double)
                {
                    DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                    df.setMaximumFractionDigits(340);
                    propertyValueJson = df.format(propertyValue);
                }
                else
                {
                    propertyValueJson = objectToJson(propertyValue);
                }

                json.append(propertySeparator).append(propertyName).append(" : ").append(propertyValueJson);
                propertySeparator = ",\n";
            }
        }
        json.append("}\n");
        return json.toString();
    }

}
