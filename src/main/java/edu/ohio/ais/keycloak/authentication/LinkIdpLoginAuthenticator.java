/*
 * Copyright 2016 Ohio University and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Based on examples from the Keycloak project, Copyright
 * Red Hat Inc. and available at https://github.com/keycloak/keycloak.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.ohio.ais.keycloak.authentication;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;

import org.keycloak.authentication.authenticators.broker.AbstractIdpAuthenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

/**
 * Automatically link the logged in account with an existing
 * or newly created user rather than prompting the user.
 *
 * @author Ilya Kogan
 */
public class LinkIdpLoginAuthenticator implements Authenticator {
    
    public void authenticate(AuthenticationFlowContext context) {
        System.out.println("Auto-linking IdP login to federated identity.");
        UserModel existingUser = AbstractIdpAuthenticator.getExistingUser(
        		context.getSession(), context.getRealm(), context.getAuthenticationSession());

        if(existingUser != null) {
            context.setUser(existingUser);
            context.success();
        } else {
            context.failure(AuthenticationFlowError.UNKNOWN_USER);
        }
    }

    public void action(AuthenticationFlowContext context) {

    }

    public boolean requiresUser() {
        return false;
    }

    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {

    }

    public void close() {

    }
}
