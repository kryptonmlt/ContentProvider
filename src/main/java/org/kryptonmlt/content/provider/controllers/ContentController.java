package org.kryptonmlt.content.provider.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.kryptonmlt.content.provider.pojos.Content;
import org.kryptonmlt.content.provider.repositories.ContentFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kurt
 */
@RestController()
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentFileRepository contentsFileRepository;

    @RequestMapping(value = "/{type}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Content> getAllContent(@PathVariable String type) {
        return contentsFileRepository.getContents(type);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, headers = "Accept=application/json")
    public Set<String> getAllTypes() {
        return contentsFileRepository.getTypes();
    }

    @RequestMapping(value = "/{type}/latest/{number}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Content> getLatestContent(@PathVariable String type, @PathVariable int number) {
        List<Content> content = contentsFileRepository.getContents(type);
        List<Content> result = new ArrayList<>();
        int max = number;
        if (number > content.size()) {
            max = content.size();
        }
        for (int i = 0; i < max; i++) {
            result.add(content.get(i));
        }
        return result;
    }

}
