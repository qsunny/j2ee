package com.aaron.junit4spring.service.impl;

import com.aaron.junit4spring.service.DataModelService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/20.
 */
@Service("ml")
public class MachineLearningService implements DataModelService {
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
