package com.Service;

import com.Mapper.FansMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FansService {
    @Autowired
    private FansMapper fansMapper;

}
