/*
 * (C) Copyright 2013 Scoop IT SAS (http://scoop.it/) and others.
 *
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
 * 
 * Contributors:
 *     Philippe GASSMANN
 *     Jean-Baptiste BELLET
 */
package com.scoopit.weedfs.client;

public class WeedFSFile {

    public static final WeedFSFile EMPTY = new WeedFSFile(null, 0);

    public final String fid;
    private long volumeId = 0;
    private String cookie = null;
    public int version = 0;

    public WeedFSFile(String fid) {
        this(fid, 0);
    }

    public WeedFSFile(String fid, int version) {
        this.fid = fid;
        this.version = version;
    }

    public String getFid() {
        return fid;
    }

    public int getVersion() {
        return version;
    }

    public long getVolumeId() {
        if (volumeId == 0 && fid != null) {
            int pos = fid.indexOf(',');
            if (pos == -1) {
                throw new IllegalArgumentException("Cannot parse fid: " + fid);
            }
            try {
                volumeId = Long.parseLong(fid.substring(0, pos));
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Cannot parse fid: " + fid, nfe);
            }
        }

        return volumeId;
    }

    public String getCookie() {
        if (cookie == null && fid != null) {
            int pos = fid.indexOf(',');
            if (pos == -1) {
                throw new IllegalArgumentException("Cannot parse fid: " + fid);
            }

            cookie = fid.substring(pos + 1);
        }

        return cookie;
    }

    @Override
    public String toString() {
        return "WeedFSFile [fid=" + fid + ", version=" + version + "]";
    }

    public String getFidWithVersion() {
        return fid + "_" + version;
    }

}
