/*
 * Copyright [2013] [Cloud4SOA, www.cloud4soa.eu]
 *
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package eu.cloud4soa.adapter.rest.response;

import java.io.Serializable;

import eu.cloud4soa.adapter.rest.response.model.SshKey;

public class CreateSSHKeyResponse extends Response<CreateSSHKeyResponse> implements Serializable{
	private static final long serialVersionUID = 9143893441674368938L;

	private SshKey sshKey;
	
	public CreateSSHKeyResponse(){
	}

	public SshKey getSshKey() {
		return sshKey;
	}

	public void setSshKey(SshKey sshKey) {
		this.sshKey = sshKey;
	}

	@Override
	public String toString() {
		return "CreateSSHKeyResponse [sshKey=" + sshKey + ", Response=" + super.toString() + "]";
	}
}
