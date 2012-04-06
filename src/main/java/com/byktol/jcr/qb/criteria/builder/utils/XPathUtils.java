/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.byktol.jcr.qb.criteria.builder.utils;

import java.util.Calendar;

import org.apache.jackrabbit.util.ISO8601;
import org.apache.jackrabbit.util.ISO9075;

import com.google.common.base.Strings;

/**
 * Collection of XPath-specific utility methods with the purpose of keeping my
 * sanity intact.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public final class XPathUtils
{

  /** The symbol used by XPath to query properties/attributes from a node. */
  private static final String xpathPropertySymbol = "@";

  /** Private constructor to avoid instantiation. */
  private XPathUtils()
  {
    // nothing here
  }

  /**
   * Prepends the at (@) symbol to the property name. This is XPath-specific.
   * <p>
   * Before you start to hate me, please be aware that I created this method
   * because of the way XPath can be used to query the JCR. For example, if you
   * have a query in which you want to evaluate a property of a child node, say
   * "jcr:content/@jcr:title," prepending the @ symbol to the property name
   * would only cause problems, i.e. "@jcr:content/@jcr:title." The issue being
   * that jcr:content is a child node, not a property, thus the query fails
   * miserably. <b>STOP DOING THAT AND I MAY CONSIDER REMOVING THIS METHOD</b>
   * <p>
   * As a measure to avoid the auto-symbol-prepending I'm going to check if the
   * symbol exists in the property name and prepend it if and only if it doesn't
   * exist. Thus, "published" would output "@published" and
   * "jcr:content/@jcr:title" would remain unaltered.
   * <p>
   * As a side-effect, if a property starts with the at (@) symbol, it is not
   * prepended again, but this wouldn't be the right way of using the API, i. e.
   * never prepend the at (@) symbol because it's XPath-specific.
   *
   * @param propertyName
   *          The name of the property that @ will be prepended to
   * @return A new string representing a valid XPath property
   */
  public static String prependSymbol(final String propertyName)
  {
    if (propertyName.contains(xpathPropertySymbol))
    {

      return propertyName;

    } else
    {
      // Performance problems ensue
      return xpathPropertySymbol + propertyName;
    }
  }

  /**
   * According to http://www.day.com/specs/jcr/1.0/6.6.4.9_Escaping.html we have
   * to escape quotation marks with another quotation mark and apostrophes with
   * another apostrophe. Why? Because the thing interprets two of them as one.
   * <p>
   * According to http://markmail.org/message/rvbu2wqmnyvbfe54 the timestamp
   * format must be in ISO8601. Since JackRabbit includes a utility class for
   * this purpose, we just make use of it.
   *
   * @param value
   *          Any object whose value will be rendered as a string
   * @return A string representing the XPath-equivalent value
   */
  public static String typedValue(final Object value)
  {
    if (value instanceof String)
    {

      return String.format(
        "'%s'",
        ((String) value).replaceAll("'", "''").replaceAll("\"", "\"\"")
      );

    } else if (value instanceof Calendar)
    {

      return String.format("xs:dateTime('%s')",
        ISO8601.format((Calendar) value)
      );

    }

    return value.toString();
  }

  /**
   * Because it's a feature in JCR to create nodes starting with numbers but not
   * being able to query them using XPath, we have to encode the node that
   * starts with a number. See: https://issues.apache.org/jira/browse/JCR-579
   * <p>
   * Ok, let's try explaining it a little bit better. If you have something like
   * "/content/2009" for your XPath query, it won't work. Why? Because it's a
   * feature. XML doesn't allow elements starting with numbers, say &lt;2009&gt;
   * and since XPath is meant to work with XML, it doesn't support them either.
   * <p>
   * Luckily, the nice folks at JackRabbit offer a utility class to encode the
   * node that starts with a number, making your XPath query valid. I'm starting
   * to see a pattern with these JackRabbit utilities.
   * <p>
   * Anyhow, the reason for this method is to not pollute the rest of the
   * implementation with JackRabbit dependencies.
   *
   * @param path
   *          The path (or node name) that is going to be encoded
   * @return An encoded path (or node name) if it starts (or has a node
   *         starting) with a number
   */
  public static String encodePath(final String path)
  {
    return ISO9075.encodePath(Strings.nullToEmpty(path));
  }

}
