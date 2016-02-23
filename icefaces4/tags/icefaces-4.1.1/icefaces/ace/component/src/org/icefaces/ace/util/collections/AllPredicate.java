/*
 * Copyright 2004-2014 ICEsoft Technologies Canada Corp.
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

package org.icefaces.ace.util.collections;


import java.io.Serializable;
import java.util.Collection;

public final class AllPredicate implements  Predicate,
        PredicateDecorator, Serializable {

    /** Serial version UID */
    private static final long serialVersionUID = -3094696765038308799L;

    /** The array of predicates to call */
    private final Predicate[] iPredicates;

    /**
     * Factory to create the predicate.
     * <p>
     * If the array is size zero, the predicate always returns true.
     * If the array is size one, then that predicate is returned.
     *
     * @param predicates  the predicates to check, cloned, not null
     * @return the <code>all</code> predicate
     * @throws IllegalArgumentException if the predicates array is null
     * @throws IllegalArgumentException if any predicate in the array is null
     */
    public static Predicate getInstance(Predicate[] predicates) {
        FunctorUtils.validate(predicates);
        if (predicates.length == 0) {
            return TruePredicate.INSTANCE;
        }
        if (predicates.length == 1) {
            return predicates[0];
        }
        predicates = FunctorUtils.copy(predicates);
        return new AllPredicate(predicates);
    }

    /**
     * Factory to create the predicate.
     * <p>
     * If the collection is size zero, the predicate always returns true.
     * If the collection is size one, then that predicate is returned.
     *
     * @param predicates  the predicates to check, cloned, not null
     * @return the <code>all</code> predicate
     * @throws IllegalArgumentException if the predicates array is null
     * @throws IllegalArgumentException if any predicate in the array is null
     */
    public static Predicate getInstance(Collection predicates) {
        Predicate[] preds = FunctorUtils.validate(predicates);
        if (preds.length == 0) {
            return TruePredicate.INSTANCE;
        }
        if (preds.length == 1) {
            return preds[0];
        }
        return new AllPredicate(preds);
    }

    /**
     * Constructor that performs no validation.
     * Use <code>getInstance</code> if you want that.
     *
     * @param predicates  the predicates to check, not cloned, not null
     */
    public AllPredicate(Predicate[] predicates) {
        super ();
        iPredicates = predicates;
    }

    /**
     * Evaluates the predicate returning true if all predicates return true.
     *
     * @param object  the input object
     * @return true if all decorated predicates return true
     */
    public boolean evaluate(Object object) {
        for (int i = 0; i < iPredicates.length; i++) {
            if (iPredicates[i].evaluate(object) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the predicates, do not modify the array.
     *
     * @return the predicates
     * @since Commons Collections 3.1
     */
    public Predicate[] getPredicates() {
        return iPredicates;
    }

}
