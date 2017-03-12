package com.app;

import static com.app.util.CommonUtils.composeId;
import static com.app.util.CommonUtils.convertFileToStringList;
import static com.app.util.Constant.EVENT_SPACE;
import static com.app.util.Constant.TICKET_SPACE;
import static com.app.util.Constant.USER_SPACE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

import com.app.entity.impl.EventImpl;
import com.app.entity.impl.TicketImpl;
import com.app.entity.impl.UserImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Storage {

    private static final Logger LOGGER = Logger.getLogger(Storage.class);
    private static final String SDF = "yyyy-MM-dd";
    private Map<String, Object> storageMap = new HashMap<String, Object>();
    private Resource fileResource;

    public Resource getFileResource() {
        return fileResource;
    }

    public void setFileResource(Resource fileResource) {
        this.fileResource = fileResource;
    }

    public Object get(String key) {
        return storageMap.get(key);
    }

    public Object put(String key, Object value) {
        return storageMap.put(key, value);
    }

    public Object remove(String key) {
        return storageMap.remove(key);
    }

    public Set<String> getAllKeys() {
        return storageMap.keySet();
    }

    public void initStorage() {
        try {
            String json = convertFileToStringList(fileResource.getFile().getPath());
            Gson gson = new GsonBuilder().setDateFormat(SDF).create();
            StorageData storageData = gson.fromJson(json, StorageData.class);

            UserImpl[] users = storageData.getUsers();
            TicketImpl[] tickets = storageData.getTickets();
            EventImpl[] events = storageData.getEvents();

            if (users != null) {
                for (UserImpl currantUser : users) {
                    String key = composeId(USER_SPACE, currantUser.getId());
                    storageMap.put(key, currantUser);
                }
                LOGGER.info("users have been written to map");
            }
            if (tickets != null) {
                for (TicketImpl currantTicket : tickets) {
                    String key = composeId(TICKET_SPACE, currantTicket.getId());
                    storageMap.put(key, currantTicket);
                }
                LOGGER.info("tickets have been written to map");
            }
            if (events != null) {
                for (EventImpl currantEvent : events) {
                    String key = composeId(EVENT_SPACE, currantEvent.getId());
                    storageMap.put(key, currantEvent);
                }
                LOGGER.info("events have been written to map");
            }

        } catch (IOException e) {
            LOGGER.error("error during reading file");
        }
    }

    public class StorageData {

        private UserImpl[] users;
        private TicketImpl[] tickets;
        private EventImpl[] events;

        public TicketImpl[] getTickets() {
            return tickets;
        }

        public EventImpl[] getEvents() {
            return events;
        }

        public UserImpl[] getUsers() {
            return users;
        }
    }
}
