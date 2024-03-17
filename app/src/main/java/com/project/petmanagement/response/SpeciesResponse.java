package com.project.petmanagement.response;

import com.project.petmanagement.model.Species;

import java.util.List;

public class SpeciesResponse {
    public class SpeciesResponse1 extends Response{
        private List<Species> data;

        public List<Species> getData() {
            return data;
        }

        public void setData(List<Species> data) {
            this.data = data;
        }
    }
}
