/*
 * This file is part of the Alitheia system, developed by the SQO-OSS
 * consortium as part of the IST FP6 SQO-OSS project, number 033331.
 *
 * Copyright 2007 - 2010 - Organization for Free and Open Source Software,  
 *                Athens, Greece.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package eu.sqooss.service.tds;

/**
 * An accessor bound to a specific project. This accessor knows 
 * the format and location of all data related to the project.
 * The accessor for a project gives the means to retrieve
 * raw project data for it. See the interfaces for each individual
 * raw data source for more information.
 *
 * An accessor has the immediate means to retrieve any of the data
 * asked for it; all the communications resources have been acquired
 * and initialized, so you can start querying immediately.
 *
 * Do not forget to release the accessor when done with it.
 *
 */
public interface ProjectAccessor {
    
    /**
     * Get the accessor project's id. 
     * @return The project id for the project this accessor is bound to
     */
    public Long getId();
    
    /**
     * Get the accessor project's name.
     * @return The project name for the project this accessor is bound to
     */
    public String getName();
    
    /**
     * Get the BTS sub-accessor for the project associated with this
     * TDAccessor.
     */
    public BTSAccessor getBTSAccessor() throws InvalidAccessorException;

    /**
     * Get the Mail sub-accessor for the project associated with this
     * TDAccessor.
     */
    public MailAccessor getMailAccessor() throws InvalidAccessorException;

    /**
     * Get the SCM sub-accessor for the project associated with this
     * TDAccessor.
     */
    public SCMAccessor getSCMAccessor() throws InvalidAccessorException;
}

// vi: ai nosi sw=4 ts=4 expandtab

