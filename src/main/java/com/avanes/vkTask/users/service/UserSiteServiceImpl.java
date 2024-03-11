package com.avanes.vkTask.users.service;

import com.avanes.vkTask.constants.CrudOperations;
import com.avanes.vkTask.log.service.LogDataService;
import com.avanes.vkTask.users.POJO.UserSite;
import com.avanes.vkTask.users.POJO.UserSiteFull;
import com.avanes.vkTask.utils.GetData;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import static com.avanes.vkTask.constants.CrudOperations.GET;
import static com.avanes.vkTask.constants.TaskConstants.USERS_URL;

@Service
@RequiredArgsConstructor
public class UserSiteServiceImpl implements UserSiteService {

    private final LogDataService log;

    @Override
    @Cacheable(cacheNames = "usersById")
    public UserSiteFull getUserSite(Long id) {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(USERS_URL + "/" + id, GET);
        Gson g = new Gson();
        UserSiteFull result = g.fromJson(builder.toString(), UserSiteFull.class);
        log.addLog(LocalDateTime.now(), USERS_URL + "/" + id, CrudOperations.GET.name(), "SUCCESS");
        return result;
    }

    @Override
    public UserSiteFull[] getUserSites() {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(USERS_URL, GET);
        Gson g = new Gson();
        log.addLog(LocalDateTime.now(), USERS_URL, CrudOperations.GET.name(), "SUCCESS");
        return g.fromJson(builder.toString(), UserSiteFull[].class);
    }

    @Override
    public UserSiteFull addUserSite(UserSite userSite) {
        UserSiteFull result = new UserSiteFull();
        Gson gson = new Gson();
        try {
            HttpResponse<String> response = GetData.INSTANCE.addData(userSite, USERS_URL);
            result = gson.fromJson(response.body(), UserSiteFull.class);
            log.addLog(LocalDateTime.now(), USERS_URL, CrudOperations.POST.name(), "SUCCESS");
        } catch (IOException | InterruptedException e) {
            log.addLog(LocalDateTime.now(), USERS_URL, CrudOperations.POST.name(), "FAIL");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public UserSiteFull patchUserSite(UserSite userSite, Long id) {
        UserSiteFull oldUserSite = getUserSite(id);
        boolean isChanged = false;
        Gson gson = new Gson();
        if (oldUserSite.equals(userSite)) {
            return oldUserSite;
        }

        if ((userSite.getName() != null) && (!userSite.getName().equals(oldUserSite.getName()))) {
            oldUserSite.setName(userSite.getName());
            isChanged = true;
        }
        if ((userSite.getUsername() != null) && (!userSite.getUsername().equals(oldUserSite.getUsername()))) {
            oldUserSite.setUsername(userSite.getUsername());
            isChanged = true;
        }
        if ((userSite.getEmail() != null) && (!userSite.getEmail().equals(oldUserSite.getEmail()))) {
            oldUserSite.setEmail(userSite.getEmail());
            isChanged = true;
        }
        if ((userSite.getAddress() != null) && (!userSite.getAddress().equals(oldUserSite.getAddress()))) {
            patchAddress(userSite.getAddress(), oldUserSite.getAddress());
            isChanged = true;
        }
        if ((userSite.getPhone() != null) && (!userSite.getPhone().equals(oldUserSite.getPhone()))) {
            oldUserSite.setPhone(userSite.getPhone());
            isChanged = true;
        }
        if ((userSite.getWebsite() != null) && (!userSite.getWebsite().equals(oldUserSite.getWebsite()))) {
            oldUserSite.setWebsite(userSite.getWebsite());
            isChanged = true;
        }
        if ((userSite.getCompany() != null) && (!userSite.getCompany().equals(oldUserSite.getCompany()))) {
            patchCompany(userSite.getCompany(), oldUserSite.getCompany());
            isChanged = true;
        }
        if (isChanged) {
            try {
                HttpResponse<String> response = GetData.INSTANCE.patchData(oldUserSite, USERS_URL + "/" + id);
                oldUserSite = gson.fromJson(response.body(), UserSiteFull.class);
                log.addLog(LocalDateTime.now(), USERS_URL, CrudOperations.PATCH.name() + "/" + id, "SUCCESS");
            } catch (IOException | InterruptedException e) {
                log.addLog(LocalDateTime.now(), USERS_URL, CrudOperations.PATCH.name() + "/" + id, "FAIL");
                throw new RuntimeException(e);
            }
        } else {
            log.addLog(LocalDateTime.now(), USERS_URL, CrudOperations.PATCH.name() + "/" + id, "FAIL: Обновление не выполнено. Новые данные соответствуют старым.");
        }
        return oldUserSite;
    }

    @Override
    public void deleteUserSite(Long id) {
        GetData.INSTANCE.deleteData(USERS_URL + "/" + id);
        log.addLog(LocalDateTime.now(), USERS_URL, CrudOperations.DELETE.name() + "/" + id, "SUCCESS");
    }

    private void patchCompany(UserSite.Company newCompany, UserSite.Company oldCompany) {
        if ((newCompany.getName() != null) && (!newCompany.getName().equals(oldCompany.getName()))) {
            oldCompany.setName(newCompany.getName());
        }
        if ((newCompany.getCatchPhrase() != null) && (!newCompany.getCatchPhrase().equals(oldCompany.getCatchPhrase()))) {
            oldCompany.setCatchPhrase(newCompany.getCatchPhrase());
        }
        if ((newCompany.getBs() != null) && (!newCompany.getBs().equals(oldCompany.getBs()))) {
            oldCompany.setBs(newCompany.getBs());
        }
    }

    private void patchAddress(UserSite.Address newAddress, UserSite.Address oldAddress) {
        if ((newAddress.getStreet() != null) && (!newAddress.getStreet().equals(oldAddress.getStreet()))) {
            oldAddress.setStreet(newAddress.getStreet());
        }
        if ((newAddress.getSuite() != null) && (!newAddress.getSuite().equals(oldAddress.getSuite()))) {
            oldAddress.setSuite(newAddress.getSuite());
        }
        if ((newAddress.getCity() != null) && (!newAddress.getCity().equals(oldAddress.getCity()))) {
            oldAddress.setCity(newAddress.getCity());
        }
        if ((newAddress.getZipcode() != null) && (!newAddress.getZipcode().equals(oldAddress.getZipcode()))) {
            oldAddress.setZipcode(newAddress.getZipcode());
        }
        if ((newAddress.getGeo() != null) && (!newAddress.getGeo().equals(oldAddress.getGeo()))) {
            patchGeo(newAddress.getGeo(), oldAddress.getGeo());
        }

    }

    private void patchGeo(UserSite.Address.Geo newGeo, UserSite.Address.Geo oldGeo) {
        if ((newGeo.getLat() != null) && (!newGeo.getLat().equals(oldGeo.getLat()))) {
            oldGeo.setLat(newGeo.getLat());
        }
        if ((newGeo.getLng() != null) && (!newGeo.getLng().equals(oldGeo.getLng()))) {
            oldGeo.setLng(newGeo.getLng());
        }
    }


}
