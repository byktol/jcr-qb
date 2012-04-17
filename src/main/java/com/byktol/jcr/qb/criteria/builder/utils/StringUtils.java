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

/**
 * @since 1.0
 * @author Victor Alvarez
 */
public final class StringUtils
{
  /** Private constructor. */
  private StringUtils()
  {
    // nothing here
  }

  /**
   * According to http://www.day.com/specs/jcr/1.0/6.6.4.9_Escaping.html we have
   * to escape quotation marks with another quotation mark and apostrophes with
   * another apostrophe. Why? Because the thing interprets two of them as one.
   *
   * @param string
   *          The string being escaped
   * @return An escaped string
   */
  public static String escapeString(final String string)
  {
    return String.format(
      "'%s'",
      ((String) string).replaceAll("'", "''").replaceAll("\"", "\"\"")
    );
  }
}
