/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.helger.jcodemodel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Case statement
 */
public class JCase implements IJStatement
{
  /**
   * label part of the case statement
   */
  private final IJExpression _label;

  /**
   * is this a regular case statement or a default case statement?
   */
  private boolean _isDefaultCase = false;

  /**
   * JBlock of statements which makes up body of this While statement
   */
  private JBlock _body;

  /**
   * Construct a case statement
   */
  protected JCase (@Nonnull final IJExpression label)
  {
    this (label, false);
  }

  /**
   * Construct a case statement. If isDefaultCase is true, then label should be
   * null since default cases don't have a label.
   */
  protected JCase (@Nullable final IJExpression label, final boolean isDefaultCase)
  {
    this._label = label;
    this._isDefaultCase = isDefaultCase;
  }

  @Nullable
  public IJExpression label ()
  {
    return _label;
  }

  public boolean isDefaultCase ()
  {
    return _isDefaultCase;
  }

  @Nonnull
  public JBlock body ()
  {
    if (_body == null)
      _body = new JBlock (false, true);
    return _body;
  }

  public void state (@Nonnull final JFormatter f)
  {
    f.indent ();
    if (!_isDefaultCase)
    {
      f.print ("case ").generable (_label).print (':').newline ();
    }
    else
    {
      f.print ("default:").newline ();
    }
    if (_body != null)
      f.statement (_body);
    f.outdent ();
  }
}
