/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.filesystem.predicates.validators.internal;

import org.jclouds.filesystem.predicates.validators.FilesystemBlobKeyValidator;

import com.google.inject.Singleton;

import java.io.File;
import java.util.Arrays;

/**
 * Validates name for filesystem container blob keys implementation
 *
 * @see org.jclouds.rest.InputParamValidator
 * @see org.jclouds.predicates.Validator
 */
@Singleton
public class FilesystemBlobKeyValidatorImpl extends FilesystemBlobKeyValidator {

    @Override
    public void validate(String name) throws IllegalArgumentException {
        //blob key cannot be null or empty
        if (name == null || name.length() < 1)
            throw new IllegalArgumentException("Blob key can't be null or empty");

        //blobkey cannot start with / (or \ in Windows) character
        if (name.startsWith("\\") || name.startsWith("/"))
            throw new IllegalArgumentException("Blob key '" + name + "' cannot start with \\ or /");
        if (Arrays.asList(name.split(File.separator.equals("\\") ? "\\\\" : File.separator)).contains(".."))
            throw new IllegalArgumentException("Blob key '" + name + "' cannot contain ../");
    }

}
