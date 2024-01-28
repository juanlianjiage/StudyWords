package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Cet4Word;
import com.example.english_test.entity.WordsSelect;
import com.example.english_test.mapper.cet4WordsMapper;
import com.example.english_test.service.ICet4WordService;
import com.example.english_test.utils.USerHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Cet4WordServiceImpl extends ServiceImpl<cet4WordsMapper, Cet4Word> implements ICet4WordService {

    @Override
    public Result get_CET4Words_List() {


        UserDTO user = USerHolder.getUser();
        int studyed = user.getStudyed();
        int count = user.getCount();
        LambdaQueryWrapper<Cet4Word> queryWrapper = new LambdaQueryWrapper<>();

            queryWrapper.last("limit "+studyed+","+count);


        List<Cet4Word> words = list(queryWrapper);

        ArrayList<WordsSelect> wordsSelects = getWordsSelects(words);
        return Result.ok(wordsSelects);
    }
    /*处理抽取的单词,包装成包含一个正确选项和三个错误选项的单词
     *
     * */
    public static ArrayList<WordsSelect> getWordsSelects(List<Cet4Word> words) {
        ArrayList<WordsSelect> wordsSelects = new ArrayList<>();
        Random random = new Random();
        for (int i1 = 0; i1 < words.size(); i1++) {
            WordsSelect wordsSelect = new WordsSelect();
            wordsSelect.setWordId(words.get(i1).getWordId());
            wordsSelect.setWordSpell(words.get(i1).getWordSpell());
            wordsSelect.setWordMeaning(words.get(i1).getWordMeaning());
            wordsSelect.setWordPhonetic(words.get(i1).getWordPhonetic());
            wordsSelect.setWordExample(words.get(i1).getWordExample());
            wordsSelect.setTranslation(words.get(i1).getTranslation());
            HashSet<String> selects1 = new HashSet<>();
            selects1.add(words.get(i1).getWordMeaning());
            int j=0;
            while(j<3)
            {
                if (selects1.add(words.get(random.nextInt(words.size())).getWordMeaning()))
                {
                    j++;
                }
            }

            ArrayList<String> select = new ArrayList<>(selects1);
            Collections.shuffle(select);
            wordsSelect.setErrorMeaning(select);
            wordsSelects.add(wordsSelect);
        }
        System.out.println(words);
        System.out.println(wordsSelects);
        return wordsSelects;
    }
}
