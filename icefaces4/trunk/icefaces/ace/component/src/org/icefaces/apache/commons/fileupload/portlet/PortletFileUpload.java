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
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions andd
 * limitations under the License.
 */
package org.icefaces.apache.commons.fileupload.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;

import org.icefaces.apache.commons.fileupload.FileItemFactory;
import org.icefaces.apache.commons.fileupload.FileItemIterator;
import org.icefaces.apache.commons.fileupload.FileUpload;
import org.icefaces.apache.commons.fileupload.FileUploadBase;
import org.icefaces.apache.commons.fileupload.FileUploadException;

/**
 * <p>High level API for processing file uploads.</p>
 *
 * <p>This class handles multiple files per single HTML widget, sent using
 * <code>multipart/mixed</code> encoding type, as specified by
 * <a href="http://www.ietf.org/rfc/rfc1867.txt">RFC 1867</a>.  Use {@link
 * #parseRequest(javax.servlet.http.HttpServletRequest)} to acquire a list
 * of {@link org.icefaces.apache.commons.fileupload.FileItem FileItems} associated
 * with a given HTML widget.</p>
 *
 * <p>How the data for individual parts is stored is determined by the factory
 * used to create them; a given part may be in memory, on disk, or somewhere
 * else.</p>
 *
 * @author <a href="mailto:Rafal.Krzewski@e-point.pl">Rafal Krzewski</a>
 * @author <a href="mailto:dlr@collab.net">Daniel Rall</a>
 * @author <a href="mailto:jvanzyl@apache.org">Jason van Zyl</a>
 * @author <a href="mailto:jmcnally@collab.net">John McNally</a>
 * @author <a href="mailto:martinc@apache.org">Martin Cooper</a>
 * @author Sean C. Sullivan
 *
 * @since FileUpload 1.1
 *
 * @version $Id: PortletFileUpload.java 479484 2006-11-27 01:06:53Z jochen $
 */
public class PortletFileUpload extends FileUpload {

    // ---------------------------------------------------------- Class methods


    /**
     * Utility method that determines whether the request contains multipart
     * content.
     *
     * @param request The portlet request to be evaluated. Must be non-null.
     *
     * @return <code>true</code> if the request is multipart;
     *         <code>false</code> otherwise.
     */
    public static final boolean isMultipartContent(ActionRequest request) {
        return FileUploadBase.isMultipartContent(
                new PortletRequestContext(request));
    }


    // ----------------------------------------------------------- Constructors


    /**
     * Constructs an uninitialised instance of this class. A factory must be
     * configured, using <code>setFileItemFactory()</code>, before attempting
     * to parse requests.
     *
     * @see FileUpload#FileUpload(FileItemFactory)
     */
    public PortletFileUpload() {
        super();
    }


    /**
     * Constructs an instance of this class which uses the supplied factory to
     * create <code>FileItem</code> instances.
     *
     * @see FileUpload#FileUpload()
     * @param fileItemFactory The factory to use for creating file items.
     */
    public PortletFileUpload(FileItemFactory fileItemFactory) {
        super(fileItemFactory);
    }


    // --------------------------------------------------------- Public methods


    /**
     * Processes an <a href="http://www.ietf.org/rfc/rfc1867.txt">RFC 1867</a>
     * compliant <code>multipart/form-data</code> stream.
     *
     * @param request The portlet request to be parsed.
     *
     * @return A list of <code>FileItem</code> instances parsed from the
     *         request, in the order that they were transmitted.
     *
     * @throws FileUploadException if there are problems reading/parsing
     *                             the request or storing files.
     */
    public List /* FileItem */ parseRequest(ActionRequest request)
            throws FileUploadException {
        return parseRequest(new PortletRequestContext(request));
    }

    /**
     * Processes an <a href="http://www.ietf.org/rfc/rfc1867.txt">RFC 1867</a>
     * compliant <code>multipart/form-data</code> stream.
     *
     * @param request The portlet request to be parsed.
     *
     * @return An iterator to instances of <code>FileItemStream</code>
     *         parsed from the request, in the order that they were
     *         transmitted.
     *
     * @throws FileUploadException if there are problems reading/parsing
     *                             the request or storing files.
     * @throws IOException An I/O error occurred. This may be a network
     *   error while communicating with the client or a problem while
     *   storing the uploaded content.
     */
    public FileItemIterator getItemIterator(ActionRequest request)
            throws FileUploadException, IOException {
        return super.getItemIterator(new PortletRequestContext(request));
    }
}
