/* eslint-disable react-hooks/rules-of-hooks */
import React from "react";
import {BaseRecord,} from "@pankod/refine-core";
import {DateField, DeleteButton, EditButton, List, ShowButton, Space, Table, useTable,} from "@pankod/refine-antd";
import {Decider} from "./Decider";

export const ListDecider: Decider = (fields) => ({
    name, canEdit, canDelete, canShow, canCreate

} ) => {
    const { tableProps } = useTable({
        syncWithLocation: true,
    });


    return (
        <List canCreate={true}>
            <Table {...tableProps} rowKey="id">
                <>
                    {
                        fields.map((field) => {
                            if(field.displayOnly && !field.displayOnly.includes("index") ) return null

                            switch(field.type) {
                                case "number":
                                case "text":
                                    return <Table.Column dataIndex={field.key} title={field.displayName || field.key}/>
                                case "date":
                                    return <Table.Column dataIndex={field.key} title={field.displayName || field.key}
                                                         render={(value: any) =>
                                                             <DateField value={value[field.key]} format={"YY/MM/DD h:mm A"}></DateField>
                                        }
                                    />
                                default:
                                    return null
                            }
                        })
                    }
                    <Table.Column
                        title="Actions"
                        dataIndex="actions"
                        render={(_, record: BaseRecord) => (
                            <Space>
                                { canShow && <ShowButton hideText size="small" recordItemId={record.id}/> }
                                { canEdit && <EditButton hideText size="small" recordItemId={record.id}/> }
                                { canDelete && <DeleteButton hideText size="small" recordItemId={record.id}/> }
                            </Space>
                        )}
                    />
                </>
            </Table>
        </List>
    );
};
