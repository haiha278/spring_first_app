package com.example.spring_first_app.service;

import com.example.spring_first_app.dto.GroupDTO;
import com.example.spring_first_app.dto.StudentDTO;
import feign.Param;
import feign.RequestLine;

import java.util.ArrayList;

public interface GroupService {
    @RequestLine("GET")
    ArrayList<GroupDTO> getAllGroups();

    @RequestLine("GET/{id}")
    GroupDTO getGroupByID(@Param("id") Integer id);

    @RequestLine("POST")
    GroupDTO insertSGroup(GroupDTO groupDTO);

    @RequestLine("PUT/{id}")
    GroupDTO updateGroupById(@Param("id") Integer id, GroupDTO groupDTO);

    @RequestLine("DELETE/{id}")
    boolean deleteGroupById(@Param("id") Integer id);
}
