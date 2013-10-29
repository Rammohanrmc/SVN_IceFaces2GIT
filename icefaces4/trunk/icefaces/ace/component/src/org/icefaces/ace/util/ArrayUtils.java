/*
 * Copyright 2004-2013 ICEsoft Technologies Canada Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions andd
 * limitations under the License.
 */

package org.icefaces.ace.util;

import java.lang.reflect.Array;


/**
 * Utility class for managing arrays
 *
 * @author Anton Koinov (latest modification by $Author: grantsmith $)
 * @version $Revision: 169655 $ $Date: 2005-05-11 12:45:06 -0400 (Wed, 11 May
 *          2005) $
 */
public class ArrayUtils {
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    //~ Constructors -------------------------------------------------------------------------------

    protected ArrayUtils() {
        // hide from public access
    }

    //~ Methods ------------------------------------------------------------------------------------

    public static Class commonClass(Class c1, Class c2) {
        if (c1 == c2) {
            return c1;
        }

        if ((c1 == Object.class) || c1.isAssignableFrom(c2)) {
            return c1;
        }

        if (c2.isAssignableFrom(c1)) {
            return c2;
        }

        if (c1.isPrimitive() || c2.isPrimitive()) {
            // REVISIT: we could try to autoconvert to Object or something appropriate
            throw new IllegalArgumentException(
                    "incompatible types " + c1 + " and " + c2);
        }

        // REVISIT: we could try to find a common supper class or interface
        return Object.class;
    }

    /**
     * Concatenates two arrays into one. If arr1 is null or empty, returns arr2.
     * If arr2 is null or empty, returns arr1. May return null if both arrays
     * are null, or one is empty and the other null. <br> The concatenated array
     * has componentType which is compatible with both input arrays (or
     * Object[])
     *
     * @param arr1 input array
     * @param arr2 input array
     * @return Object the concatenated array, elements of arr1 first
     */
    public static Object concat(Object arr1, Object arr2) {
        int len1 = (arr1 == null) ? (-1) : Array.getLength(arr1);

        if (len1 <= 0) {
            return arr2;
        }

        int len2 = (arr2 == null) ? (-1) : Array.getLength(arr2);

        if (len2 <= 0) {
            return arr1;
        }

        Class commonComponentType =
                commonClass(arr1.getClass().getComponentType(),
                            arr2.getClass().getComponentType());
        Object newArray = Array.newInstance(commonComponentType, len1 + len2);
        System.arraycopy(arr1, 0, newArray, 0, len1);
        System.arraycopy(arr2, 0, newArray, len1, len2);

        return newArray;
    }

    /**
     * Concatenates arrays into one. Any null or empty arrays are ignored. If
     * all arrays are null or empty, returns null. Elements will be ordered in
     * the order in which the arrays are supplied.
     *
     * @param arrs array of arrays
     * @return the concatenated array
     */
    public static Object concat(Object[] arrs) {
        int totalLen = 0;
        Class commonComponentType = null;
        for (int i = 0, len = arrs.length; i < len; i++) {
            // skip all null arrays
            if (arrs[i] == null) {
                continue;
            }

            int arrayLen = Array.getLength(arrs[i]);

            // skip all empty arrays
            if (arrayLen == 0) {
                continue;
            }

            totalLen += arrayLen;

            Class componentType = arrs[i].getClass().getComponentType();
            commonComponentType =
                    (commonComponentType == null) ? componentType
                    : commonClass(commonComponentType, componentType);
        }

        if (commonComponentType == null) {
            return null;
        }

        return concat(Array.newInstance(commonComponentType, totalLen),
                      totalLen, arrs);
    }

    public static Object concat(Object toArray, int totalLen, Object[] arrs) {
        if (totalLen == 0) {
            // Should we allocate an empty array instead?
            return toArray;
        }

        if (totalLen > Array.getLength(toArray)) {
            toArray = Array.newInstance(toArray.getClass().getComponentType(),
                                        totalLen);
        }

        for (int i = 0, len = arrs.length, offset = 0; i < len; i++) {
            final Object arr = arrs[i];
            if (arr != null) {
                int arrayLen = Array.getLength(arr);
                if (arrayLen > 0) {
                    System.arraycopy(arr, 0, toArray, offset, arrayLen);
                    offset += arrayLen;
                }
            }
        }

        return toArray;
    }

    public static Object concat(Object arr1, Object arr2, Object arr3) {
        return concat(new Object[]{arr1, arr2, arr3});
    }

    public static Object concat(Object arr1, Object arr2, Object arr3,
                                Object arr4, Object arr5) {
        return concat(new Object[]{arr1, arr2, arr3, arr4, arr5});
    }

	public static String[] concat(String[] array1, String[] array2) {
		int length1 = array1.length;
		int length2 = array2.length;
		int length = length1 + length2;

		String[] dest = new String[length];

		System.arraycopy(array1, 0, dest, 0, length1);
		System.arraycopy(array2, 0, dest, length1, length2);

		return dest;
	}

	public static String[] concat(String[]... arrays) {
		int destSize = 0;
		for (int i = 0; i < arrays.length; i++) {
			destSize += arrays[i].length;
		}
		String[] dest = new String[destSize];
		int lastIndex = 0;
		for (int i = 0; i < arrays.length; i++) {
			String[] array = arrays[i];
			System.arraycopy(array, 0, dest, lastIndex, array.length);
			lastIndex += array.length;
		}

		return dest;
	}

    public static Object concatSameType(Object toArray, Object[] arrs) {
        int totalLen = 0;
        for (int i = 0, len = arrs.length; i < len; i++) {
            if (arrs[i] != null) {
                totalLen += Array.getLength(arrs[i]);
            }
        }

        return concat(toArray, totalLen, arrs);
    }

    public static boolean contains(Object[] array, Object value) {
        if (array == null || array.length == 0) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            Object o = array[i];
            if ((o == null && value == null) ||
                (o != null && o.equals(value))) {
                return true;
            }
        }

        return false;
    }

    public static boolean contains(String[] array, String searchedText) {

		if (array == null || array.length == 0)
			return false;

		for (int i = 0; i < array.length; i++) {
			if (array[i].equalsIgnoreCase(searchedText))
				return true;
		}

		return false;
	}
}
