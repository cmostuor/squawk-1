/*
 * Copyright 2006-2008 Sun Microsystems, Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * 
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2
 * only, as published by the Free Software Foundation.
 * 
 * This code is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA
 * 
 * Please contact Sun Microsystems, Inc., 16 Network Circle, Menlo
 * Park, CA 94025 or visit www.sun.com if you need additional
 * information or have any questions.
 */

package com.sun.squawk.builder.ccompiler;

import com.sun.squawk.builder.platform.*;
import com.sun.squawk.builder.*;

/**
 * The interface to the GCC compiler on Mac OS X on the X86 platform.
 * 
 */
public class GccMacOSXX86Compiler extends GccMacOSXCompiler {

    public GccMacOSXX86Compiler(Build env, Platform platform) {
        super("gcc-macosxx86", env, platform);
    }

    /**
     * {@inheritDoc}
     */
    public String getArchitecture() {
        return "X86";
    }
    
    public String getArchOptions() {
        return "-arch i386 ";
    }

    /**
     * {@inheritDoc}
     */
    public String getRtsIncludeName() {
    	return RTS_INCLUDE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    public String options(boolean disableOpts) {
        String result = super.options(disableOpts);
        String osVer = System.getProperty("os.version");
        String SDKVer = "-isysroot /Developer/SDKs/MacOSX10.6.sdk -mmacosx-version-min=10.5 ";
        if (osVer != null && osVer.contains("10.5")) {
            SDKVer = "-isysroot /Developer/SDKs/MacOSX10.5.sdk -mmacosx-version-min=10.5 ";
        }
        result += SDKVer;
        if (!disableOpts) {
            // @issue 1390. All x86 Macs are at least Prescott class, if not nocona. But gcc doesn't seem to know that.
            result = result + "-march=prescott ";
        }
        return result;
    }

}
