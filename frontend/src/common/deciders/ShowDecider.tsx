/* eslint-disable react-hooks/rules-of-hooks */
import React from "react";
import {BaseRecord, useShow} from "@pankod/refine-core";
import {NumberField, Show, TextField, Typography,} from "@pankod/refine-antd";
import {Decider} from "./Decider";
import {Field, findField} from "../../resources/Field";

const { Title } = Typography;

export const ShowDecider: Decider = (fields) => () => {

    const { queryResult } = useShow();
    const { data, isLoading } = queryResult;

    const record = data?.data;

    return (
        <Show isLoading={isLoading}>
            {
                record && Object.keys(record).map((key: any) => {
                    const field = findField(fields, key)
                    if(!field || record[field.key] == null) return null
                    return (
                        <>
                            <Title level={5}>{field.displayName || field.key}</Title>
                            <DisplayField field={field} record={record}/>
                        </>
                    )
                })
            }
        </Show>
    );
};

const DisplayField: React.FC<{field: Field, record: BaseRecord}> = ({field, record}) => {

    switch(field.type) {
        case "number":
            return <NumberField value={record[field.key]}/>
        case "text":
            return <TextField value={record[field.key]}/>
        default:
            return null
    }
}