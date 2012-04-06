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
package com.byktol.jcr.qb;

import javax.jcr.query.Query;

/**
 * Wraps the existing query languages in JCR. The purpose is to have an enum
 * instead of a bunch of strings.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public enum QueryLanguage
{

  /** The XPath query language. */
  @SuppressWarnings("deprecation")
  XPATH(Query.XPATH),
  /** The JCR Structured Query Language (SQL). */
  @SuppressWarnings("deprecation")
  SQL(Query.SQL),
  /** The JCR Structured Query Language 2 (SQL2). */
  SQL2(Query.JCR_SQL2);

  /** The language being wrapped. */
  private String language;

  /**
   * Private constructor.
   *
   * @param lang
   *          The JCR language being wrapped.
   */
  private QueryLanguage(final String lang)
  {
    this.language = lang;
  }

  @Override
  public String toString()
  {
    return this.language;
  };
}
